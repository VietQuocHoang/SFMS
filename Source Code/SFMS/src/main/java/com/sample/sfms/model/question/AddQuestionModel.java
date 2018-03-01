package com.sample.sfms.model.question;

import com.sample.sfms.model.option.OptionCreateModel;

public class AddQuestionModel {

    private String type;
    private String suggestion;
    private int criteriaId;
    private int feedbackId;
    private boolean isRequired;
    private String questionContent;
    private OptionCreateModel[] optionCreateModel;


    public AddQuestionModel() {

    }



    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    public int getCriteriaId() {
        return criteriaId;
    }

    public void setCriteriaId(int criteriaId) {
        this.criteriaId = criteriaId;
    }

    public int getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }

    public boolean isRequired() {
        return isRequired;
    }

    public void setRequired(boolean required) {
        isRequired = required;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public OptionCreateModel[] getOptionCreateModel() {
        return optionCreateModel;
    }

    public void setOptionCreateModel(OptionCreateModel[] optionCreateModel) {
        this.optionCreateModel = optionCreateModel;
    }
}
