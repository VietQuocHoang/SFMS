package com.sample.sfms.model;

public class ModifySuggestionModel {

    private int questionId;
    private String suggestion;

    public ModifySuggestionModel() {
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }
}
