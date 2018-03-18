package com.sample.sfms.model.report.reportSemester;

import java.util.ArrayList;
import java.util.List;

public class YNQuestionReportModel {
    private int feedbackId;
    private String questionContent;
    private List<OpenAnswerReportModel> openAnswerReportModelList;
    private List<YNAnswerReportModel> ynAnswerReportModelList;
    private int responseCount;
    private String suggestion;
    private String feedbackName;
    private double questionAvgPoint;

    public YNQuestionReportModel() {
        openAnswerReportModelList = new ArrayList<>();
        ynAnswerReportModelList = new ArrayList<>();
    }

    public int getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
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

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public List<OpenAnswerReportModel> getOpenAnswerReportModelList() {
        return openAnswerReportModelList;
    }

    public void setOpenAnswerReportModelList(List<OpenAnswerReportModel> openAnswerReportModelList) {
        this.openAnswerReportModelList = openAnswerReportModelList;
    }

    public List<YNAnswerReportModel> getYnAnswerReportModelList() {
        return ynAnswerReportModelList;
    }

    public void setYnAnswerReportModelList(List<YNAnswerReportModel> ynAnswerReportModelList) {
        this.ynAnswerReportModelList = ynAnswerReportModelList;
    }

    public double getQuestionAvgPoint() {
        return questionAvgPoint;
    }

    public void setQuestionAvgPoint(double questionAvgPoint) {
        this.questionAvgPoint = questionAvgPoint;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }
}
