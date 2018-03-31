package com.sample.sfms.model.feedback.conduct;


public class EditFeedbackConductAnswerModel {
    private int id;
    private String answerContent;
    private int optionnId;

    public EditFeedbackConductAnswerModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }

    public int getOptionnId() {
        return optionnId;
    }

    public void setOptionnId(int optionnId) {
        this.optionnId = optionnId;
    }

}
