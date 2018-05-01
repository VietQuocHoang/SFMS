package com.sample.sfms.service.interf;

import com.sample.sfms.entity.Question;
import com.sample.sfms.model.ModifySuggestionModel;
import com.sample.sfms.model.question.AddQuestionModel;
import com.sample.sfms.model.question.RemoveQuestionModel;
import com.sample.sfms.model.question.UpdateQuestionModel;

import java.util.List;

/**
 * Created by MyPC on 21/02/2018.
 */

public interface QuestionService {

    /**
     * Add 1 question
     * @param model
     * @return Id của question vừa được tạo dưới db
     */
    int addQuestion(AddQuestionModel model) throws Exception;

    void updateQuestion(UpdateQuestionModel model) throws Exception;

    void removeQuestion(int questionId) throws Exception;

    List<Question> findAll();

    List<Question> findByFeedbackId(int feedbackId);

    List<Question> findByFeedbackIdASC(int feedbackId);

    Question findByQuestionID (int questionID);

    void modifySuggestion(String suggestion, int questionId) throws Exception;

}
