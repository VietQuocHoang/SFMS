package com.sample.sfms.service.impl;

import com.sample.sfms.entity.*;
import com.sample.sfms.model.ModifySuggestionModel;
import com.sample.sfms.model.option.OptionCreateModel;
import com.sample.sfms.model.option.OptionUpdateModel;
import com.sample.sfms.model.question.AddQuestionModel;
import com.sample.sfms.model.question.RemoveQuestionModel;
import com.sample.sfms.model.StaticVariables;
import com.sample.sfms.model.question.UpdateQuestionModel;
import com.sample.sfms.repository.CriteriaRepository;
import com.sample.sfms.repository.FeedbackRepository;
import com.sample.sfms.repository.QuestionRepository;
import com.sample.sfms.service.interf.OptionnService;
import com.sample.sfms.service.interf.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Binh Nguyen on 24-Feb-18.
 */
@Service("questionService")
public class QuestionServiceImpl implements QuestionService {

    static Logger logger = Logger.getLogger(RoleServiceImpl.class.getName());

    @Autowired
    private QuestionRepository questionRepo;

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private CriteriaRepository criteriaRepository;

    @Autowired
    private OptionnService optionnService;

    @Override
    @Transactional
    public int addQuestion(AddQuestionModel model) throws Exception {

        if (model.getFeedbackId() == 0) {
            throw new Exception("Xin hãy xác định rằng câu hỏi này thuộc feedback nào");
        }

        if (model.getType() == null) {
            throw new Exception("Xin hãy chọn loại câu hỏi");
        }

        if (!isValidType(model)){
            throw new Exception("Xin kiểm tra lại loại câu hỏi và các option");
        }

        Question question = new Question();
        question.setIsRequied(model.isRequired());
        question.setQuestionContent(model.getQuestionContent());
        question.setSuggestion(model.getSuggestion());
       /* if (model.getSuggestion() == null) {
            question.setSuggestion("");
        } else {
            question.setSuggestion(model.getSuggestion());
        }*/
        question.setType(model.getType());


        try {
            Feedback feedback = feedbackRepository.findById(model.getFeedbackId());
            question.setFeedbackByFeedbackId(feedback);

            Criteria criteria = criteriaRepository.findById(model.getCriteriaId());
            question.setCriteriaByCriteriaId(criteria);

            this.questionRepo.save(question);

            int id = question.getId();

            if (model.getOptionCreateModel() != null) {
                for (OptionCreateModel option : model.getOptionCreateModel()) {
                    option.setQuestion(question);
                    optionnService.add(option);
                }
                if (model.isRequireOther()) {
                    OptionCreateModel optionCreateModel = new OptionCreateModel();
                    optionCreateModel.setQuestion(question);
                    optionCreateModel.setOptionContent("Khác");
                    optionCreateModel.setPoint(0.0);
                    optionnService.add(optionCreateModel);
                }
            } else {
                OptionCreateModel optionCreateModel = new OptionCreateModel();
                optionCreateModel.setQuestion(question);
                optionCreateModel.setOptionContent("");
                optionCreateModel.setPoint(0.0);
                optionnService.add(optionCreateModel);
            }
            return id;
        } catch (Exception ex) {
            throw ex;
        }


    }

    @Override
    @Transactional
    public void modifySuggestion(String suggestion, int questionId) throws Exception {
        if (questionId == 0) {
            throw new Exception("Xin chọn câu hỏi để update");
        }

        try {
            int result = questionRepo.updateSuggestion(suggestion, questionId);
            /*Question question = this.questionRepo.getOne(model.getQuestionId());
            question.setSuggestion(model.getSuggestion());

            Feedback feedback = feedbackRepository.findById(model.getFeedbackId());

            question.setFeedbackByFeedbackId(feedback);

            this.questionRepo.save(question);*/

        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    @Transactional
    public void updateQuestion(UpdateQuestionModel model) throws Exception {
        if (model.getQuestionId() == 0) {
            throw new Exception("Xin chọn câu hỏi để update");
        }
        if (model.getFeedbackId() == 0) {
            throw new Exception("Xin hãy xác định rằng câu hỏi này thuộc feedback nào");
        }

        if (model.getType() == null) {
            throw new Exception("Xin kiểm tra lại loại câu hỏi và các option");
        }

        if (!isValidTypeUpdate(model)){
            throw new Exception("Xin kiểm tra lại các option. Nếu chọn option thì thêm đủ option.");
        }

        try {

            Question question = this.questionRepo.getOne(model.getQuestionId());

            question.setIsRequied(model.isRequired());
            question.setQuestionContent(model.getQuestionContent());
            question.setSuggestion(model.getSuggestion());
            question.setType(model.getType());
            Feedback feedback = feedbackRepository.findById(model.getFeedbackId());

            question.setFeedbackByFeedbackId(feedback);

            this.questionRepo.save(question);

            List<Integer> listModifyOptionnID = new ArrayList<>();
            if (model.getOptionUpdateModels() != null) {
                for (OptionUpdateModel option : model.getOptionUpdateModels()) {
                    option.setQuestion(question);
                    optionnService.update(option);
                    if (option.getId() >= 0) {
                        listModifyOptionnID.add(option.getId());
                    }
                }
            }

            List<Optionn> listExistedOption = optionnService.findByQuestionId(model.getQuestionId());

            for (Optionn optionn : listExistedOption) {
                if (!listModifyOptionnID.contains(optionn.getId()) && !optionn.getOptionnContent().equals("Khác") && optionn.getPoint()!=0) {
                    optionnService.remove(optionn.getId());
                }
            }

        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public void removeQuestion(int questionId) throws Exception {
     /*   if (model.getId() == 0) {
            throw new Exception("Xin chọn câu hỏi để xóa");
        }*/

        try {
            Question question = this.questionRepo.findOne(questionId);

            for (Optionn option : question.getOptionsById()) {
                this.optionnService.remove(option.getId());
            }

            this.questionRepo.delete(questionId);

        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public List<Question> findAll() {
        return questionRepo.findAll();
    }

    @Override
    public List<Question> findByFeedbackId(int feedbackId) {
        return questionRepo.findByFeedbackId(feedbackId);
    }

    @Override
    public Question findByQuestionID(int questionID) {
        return questionRepo.findByQuestionId(questionID);
    }

    /*---------------Private methods------------------*/
    private boolean isValidType(AddQuestionModel model) {
        if (Arrays.asList(StaticVariables.OPTION_NEEDED_QUESTION_TYPE).contains(model.getType()))
            return model.getOptionCreateModel() != null;
        else return model.getOptionCreateModel() == null;
    }

    private boolean isValidTypeUpdate(UpdateQuestionModel model) {
        if (Arrays.asList(StaticVariables.OPTION_NEEDED_QUESTION_TYPE).contains(model.getType()))
            return model.getOptionUpdateModels() != null;
        else return model.getOptionUpdateModels() == null;
    }

}
