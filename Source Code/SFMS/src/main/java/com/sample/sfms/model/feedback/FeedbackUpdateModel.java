package com.sample.sfms.model.feedback;

import com.sample.sfms.model.Model;
import com.sample.sfms.model.question.AddQuestionModel;
import com.sample.sfms.model.question.UpdateQuestionModel;

import java.util.List;

public class FeedbackUpdateModel extends Model {

    private int id;

    private List<UpdateQuestionModel> questions;

    public FeedbackUpdateModel(List<UpdateQuestionModel> questions) {
        this.questions = questions;
    }

    public FeedbackUpdateModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<UpdateQuestionModel> getQuestions() {
        return questions;
    }

    public void setQuestions(List<UpdateQuestionModel> questions) {
        this.questions = questions;
    }

    @Override
    public boolean valid() {
        if (this.questions == null || this.questions.size() == 0) 
            return false;

        for (UpdateQuestionModel question :
                questions) {
            if (!question.valid())
                return false;
        }

        return true;
    }
}
