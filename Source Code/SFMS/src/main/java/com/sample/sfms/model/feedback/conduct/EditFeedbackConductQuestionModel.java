package com.sample.sfms.model.feedback.conduct;

import java.util.ArrayList;
import java.util.List;

public class EditFeedbackConductQuestionModel {
    private int id;
    private String type;
    private boolean isRequied;
    private String questionContent;
    private List<EditFeedbackConductOptionModel> optionList;

    public EditFeedbackConductQuestionModel() {
        optionList = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isRequied() {
        return isRequied;
    }

    public void setRequied(boolean requied) {
        isRequied = requied;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public List<EditFeedbackConductOptionModel> getOptionList() {
        return optionList;
    }

    public void setOptionList(List<EditFeedbackConductOptionModel> optionList) {
        this.optionList = optionList;
    }
}
