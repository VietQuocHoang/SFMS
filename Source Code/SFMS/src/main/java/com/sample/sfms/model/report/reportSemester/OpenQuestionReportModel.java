package com.sample.sfms.model.report.reportSemester;

import java.util.ArrayList;
import java.util.List;

public class OpenQuestionReportModel {
    private int feedbackId;
    private String questionContent;
    private List<OpenAnswerReportModel> openAnswerReportModels;
    private int responseCount;
    private String suggestion;
    private String feedbackName;

    public OpenQuestionReportModel() {
        openAnswerReportModels = new ArrayList<>();
    }

    public int getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public List<OpenAnswerReportModel> getOpenAnswerReportModels() {
        return openAnswerReportModels;
    }

    public void setOpenAnswerReportModels(List<OpenAnswerReportModel> openAnswerReportModels) {
        this.openAnswerReportModels = openAnswerReportModels;
    }

    public int getResponseCount() {
        return responseCount;
    }

    public void setResponseCount(int responseCount) {
        this.responseCount = responseCount;
    }

    public String getFeedbackName() {
        return feedbackName;
    }

    public void setFeedbackName(String feedbackName) {
        this.feedbackName = feedbackName;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }
}
