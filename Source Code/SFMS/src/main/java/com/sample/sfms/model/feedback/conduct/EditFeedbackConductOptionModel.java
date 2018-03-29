package com.sample.sfms.model.feedback.conduct;

public class EditFeedbackConductOptionModel {
    private int id;
    private String optionnContent;
    private EditFeedbackConductAnswerModel answer;
    private boolean answered;
    private double point;

    public EditFeedbackConductOptionModel() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOptionnContent() {
        return optionnContent;
    }

    public void setOptionnContent(String optionnContent) {
        this.optionnContent = optionnContent;
    }

    public EditFeedbackConductAnswerModel getAnswer() {
        return answer;
    }

    public void setAnswer(EditFeedbackConductAnswerModel answer) {
        this.answer = answer;
    }

    public boolean isAnswered() {
        return answered;
    }

    public void setAnswered(boolean answered) {
        this.answered = answered;
    }

    public double getPoint() {
        return point;
    }

    public void setPoint(double point) {
        this.point = point;
    }
}
