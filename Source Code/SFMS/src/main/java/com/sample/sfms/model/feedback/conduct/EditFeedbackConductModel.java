package com.sample.sfms.model.feedback.conduct;

import java.util.ArrayList;
import java.util.List;

public class EditFeedbackConductModel {
    private int id;
    private String feedbackName;
    private List<EditFeedbackConductQuestionModel> questionList;

    public EditFeedbackConductModel() {
        questionList = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFeedbackName() {
        return feedbackName;
    }

    public void setFeedbackName(String feedbackName) {
        this.feedbackName = feedbackName;
    }

    public List<EditFeedbackConductQuestionModel> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<EditFeedbackConductQuestionModel> questionList) {
        this.questionList = questionList;
    }
}

