package com.sample.sfms.model.report.reportSemester;

import java.util.ArrayList;
import java.util.List;

public class CriteriaReportModel {
    private int criteriaId;
    private List<YNQuestionReportModel> ynQuestionReportModels;
    private List<OpenQuestionReportModel> openQuestionReportModels;
    private double averageCriteriaPoint;
    private String criteriaName;

    public CriteriaReportModel() {
        ynQuestionReportModels = new ArrayList<>();
        openQuestionReportModels = new ArrayList<>();
    }

    public int getCriteriaId() {
        return criteriaId;
    }

    public void setCriteriaId(int criteriaId) {
        this.criteriaId = criteriaId;
    }

    public List<YNQuestionReportModel> getYnQuestionReportModels() {
        return ynQuestionReportModels;
    }

    public void setYnQuestionReportModels(List<YNQuestionReportModel> ynQuestionReportModels) {
        this.ynQuestionReportModels = ynQuestionReportModels;
    }

    public List<OpenQuestionReportModel> getOpenQuestionReportModels() {
        return openQuestionReportModels;
    }

    public void setOpenQuestionReportModels(List<OpenQuestionReportModel> openQuestionReportModels) {
        this.openQuestionReportModels = openQuestionReportModels;
    }

    public double getAverageCriteriaPoint() {
        return averageCriteriaPoint;
    }

    public void setAverageCriteriaPoint(double averageCriteriaPoint) {
        this.averageCriteriaPoint = averageCriteriaPoint;
    }

    public String getCriteriaName() {
        return criteriaName;
    }

    public void setCriteriaName(String criteriaName) {
        this.criteriaName = criteriaName;
    }
}
