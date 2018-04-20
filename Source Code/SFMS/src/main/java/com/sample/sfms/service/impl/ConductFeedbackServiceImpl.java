package com.sample.sfms.service.impl;

import com.sample.sfms.entity.*;
import com.sample.sfms.model.answer.ConductAnswer;
import com.sample.sfms.model.answer.ConductAnswerWrapper;
import com.sample.sfms.model.feedback.conduct.EditFeedbackConductAnswerModel;
import com.sample.sfms.model.feedback.conduct.EditFeedbackConductModel;
import com.sample.sfms.model.feedback.conduct.EditFeedbackConductOptionModel;
import com.sample.sfms.model.feedback.conduct.EditFeedbackConductQuestionModel;
import com.sample.sfms.repository.*;
import com.sample.sfms.service.interf.ConductFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.persistence.RollbackException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ConductFeedbackServiceImpl implements ConductFeedbackService {

    Logger logger = Logger.getLogger(ConductFeedbackServiceImpl.class.getName());

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private OptionnRepository optionnRepository;

    @Autowired
    private UserFeedbackRepository userFeedbackRepository;

    @Override
    public ResponseEntity<Feedback> findFeedbackByid(int id) {
        Feedback feedback = feedbackRepository.findFeedbackToConduct(id, new Date(System.currentTimeMillis()));
        if (null == feedback) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            User user = getCurrentAuthenticatedUser();
            UserFeedback userFeedback = userFeedbackRepository.findUserFeedbackByUserAndFeedback(user.getId(), id);
            if (userFeedback == null) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            } else {
                if (!userFeedback.isConducted()) {
                    return new ResponseEntity<>(feedback, HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
                }
            }
        }
    }

    @Override
    public ResponseEntity saveAnswer(ConductAnswerWrapper conductAnswerWrapper) {
        User user = getCurrentAuthenticatedUser();
        if (user == null) {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
        try {
            List<Answer> oldAnswers = answerRepository.findAllAnswerByUserAndFeedback(user.getId(), conductAnswerWrapper.getFeedbackId());
            Timestamp currDate = new Timestamp(System.currentTimeMillis());
            if (conductAnswerWrapper.getAnswers() != null) {
                Optionn optionn = null;
                Answer answer;
                for (ConductAnswer conductAnswer : conductAnswerWrapper.getAnswers()) {
                    optionn = optionnRepository.findOne(conductAnswer.getOptionnByOptionnId());
                    answer = new Answer();
                    answer.setCreateDate(currDate);
                    answer.setOptionnByOptionnId(optionn);
                    answer.setUserByUserId(user);
                    answer.setAnswerContent(conductAnswer.getAnswerContent());
                    answerRepository.save(answer);
                }
                if (null != optionn) {
                    int feedbackId = optionn.getQuestionByQuestionId().getFeedbackByFeedbackId().getId();
                    UserFeedback userFeedback = userFeedbackRepository.findUserFeedbackByUserAndFeedback(user.getId(), feedbackId);
                    userFeedback.setConducted(true);
                    userFeedbackRepository.save(userFeedback);
                }
            }
            answerRepository.delete(oldAnswers);
            return new ResponseEntity(HttpStatus.OK);
        } catch (RollbackException e) {
            logger.log(Level.FINE, e.toString());
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity saveAnswerMobile(ConductAnswerWrapper conductAnswerWrapper) {
        answerRepository.removeAllAnswerByUserAndFeedback(9, conductAnswerWrapper.getFeedbackId());
        Timestamp currDate = new Timestamp(System.currentTimeMillis());
        if (conductAnswerWrapper.getAnswers() != null) {
            Optionn optionn = null;
            Answer answer;
            for (ConductAnswer conductAnswer : conductAnswerWrapper.getAnswers()) {
                optionn = optionnRepository.findOne(conductAnswer.getOptionnByOptionnId());
                answer = new Answer();
                answer.setCreateDate(currDate);
                answer.setOptionnByOptionnId(optionn);
                answer.setUserByUserId(userRepository.findOne(9));
                answer.setAnswerContent(conductAnswer.getAnswerContent());
                answerRepository.save(answer);
            }
            if (null != optionn) {
                int feedbackId = optionn.getQuestionByQuestionId().getFeedbackByFeedbackId().getId();
                UserFeedback userFeedback = userFeedbackRepository.findUserFeedbackByUserAndFeedback(9, feedbackId);
                userFeedback.setConducted(true);
                userFeedbackRepository.save(userFeedback);
            }
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @Override
    public ResponseEntity findFeedbackToEdit(int feedbackId) {
        User user = getCurrentAuthenticatedUser();
        UserFeedback userFeedback = userFeedbackRepository.findUserFeedbackByUserAndFeedback(user.getId(), feedbackId);
        if (userFeedback == null) {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        } else {
            List<Answer> answerList = answerRepository.getAllAnswerByUserIdAndFeedbackId(user.getId(), feedbackId);
            if (answerList == null || answerList.isEmpty()) {
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            } else {
                Feedback feedback = feedbackRepository.findById(feedbackId);
                EditFeedbackConductModel editFeedbackConductModel = new EditFeedbackConductModel();
                editFeedbackConductModel.setFeedbackName(feedback.getFeedbackName());
                editFeedbackConductModel.setId(feedback.getId());
                EditFeedbackConductQuestionModel questionModel;
                for (Question q : feedback.getQuestionsById()) {
                    questionModel = new EditFeedbackConductQuestionModel();
                    questionModel.setType(q.getType());
                    questionModel.setId(q.getId());
                    questionModel.setRequied(q.getIsRequied());
                    questionModel.setQuestionContent(q.getQuestionContent());
                    EditFeedbackConductOptionModel optionModel;
                    for (Optionn o : q.getOptionsById()) {
                        optionModel = new EditFeedbackConductOptionModel();
                        optionModel.setOptionnContent(o.getOptionnContent());
                        optionModel.setId(o.getId());
                        optionModel.setPoint(o.getPoint());
                        questionModel.getOptionList().add(optionModel);
                        EditFeedbackConductAnswerModel answerModel;
                        for (Answer a : answerList) {
                            if (a.getOptionnByOptionnId().getId() == o.getId()) {
                                answerModel = new EditFeedbackConductAnswerModel();
                                answerModel.setId(a.getId());
                                answerModel.setOptionnId(o.getId());
                                answerModel.setAnswerContent(a.getAnswerContent());
                                optionModel.setAnswer(answerModel);
                                optionModel.setAnswered(true);
                            }
                        }
                    }
                    editFeedbackConductModel.getQuestionList().add(questionModel);
                }
                return new ResponseEntity<>(editFeedbackConductModel, HttpStatus.OK);
            }
        }
    }

    private User getCurrentAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userRepository.findByUsername(username);
    }

}
