package com.sample.sfms.service.impl;

import com.sample.sfms.entity.*;
import com.sample.sfms.model.AddQuestionModel;
import com.sample.sfms.model.RemoveQuestionModel;
import com.sample.sfms.model.UpdateQuestionModel;
import com.sample.sfms.repository.FeedbackRepository;
import com.sample.sfms.repository.QuestionRepository;
import com.sample.sfms.service.interf.FeedbackService;
import com.sample.sfms.service.interf.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.UnexpectedRollbackException;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Binh Nguyen on 24-Feb-18.
 */
@Service("questionService")
public class QuestionServiceImpl implements QuestionService {

    static Logger logger = Logger.getLogger(QuestionServiceImpl.class.getName());

    @Autowired
    private QuestionRepository questionRepo;

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Override
    public int addQuestion(AddQuestionModel model) throws Exception {

        if (model.getFeedbackId() == 0) {
            throw new Exception("Xin hãy xác định rằng câu hỏi này thuộc feedback nào");
        }

        if (model.getType() == null) {
            throw new Exception("Xin hãy chọn loại câu hỏi");
        }

        Question question = new Question();
        question.setIsRequied(model.isRequired());
        question.setQuestionContent(model.getQuestionContent());
        question.setSuggestion(model.getSuggestion());
        question.setType(model.getType());

        Feedback feedback = feedbackRepository.findById(model.getFeedbackId());

        question.setFeedbackByFeedbackId(feedback);

        this.questionRepo.save(question);
        return question.getId();
    }

    @Override
    public void updateQuestion(UpdateQuestionModel model) throws Exception {
        if (model.getId() == 0) {
            throw new Exception("Xin chọn câu hỏi để update");
        }
        if (model.getFeedbackId() == 0) {
            throw new Exception("Xin hãy xác định rằng câu hỏi này thuộc feedback nào");
        }

        if (model.getType() == null) {
            throw new Exception("Xin hãy chọn loại câu hỏi");
        }

        try {
            Question question = this.questionRepo.getOne(model.getId());

            question.setIsRequied(model.isRequired());
            question.setQuestionContent(model.getQuestionContent());
            question.setSuggestion(model.getSuggestion());
            question.setType(model.getType());
            Feedback feedback = feedbackRepository.findById(model.getFeedbackId());

            question.setFeedbackByFeedbackId(feedback);

            this.questionRepo.save(question);

        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public void removeQuestion(RemoveQuestionModel model) throws Exception {
        if (model.getId() == 0) {
            throw new Exception("Xin chọn câu hỏi để xóa");
        }

        try {
            this.questionRepo.delete(model.getId());

        } catch (Exception ex) {
            throw ex;
        }
    }
}
