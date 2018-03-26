package com.sample.sfms.model;

import com.sample.sfms.model.question.UpdateQuestionModel;

import java.util.List;

public class FeedbackModifySuggestionModel extends Model {
    private int id;

    private List<ModifySuggestionModel> questions;

    public FeedbackModifySuggestionModel() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<ModifySuggestionModel> getQuestions() {
        return questions;
    }

    public void setQuestions(List<ModifySuggestionModel> questions) {
        this.questions = questions;
    }

    @Override
    public boolean valid() {
        if (this.questions == null || this.questions.size() == 0)
            return false;

        return true;
    }
}
