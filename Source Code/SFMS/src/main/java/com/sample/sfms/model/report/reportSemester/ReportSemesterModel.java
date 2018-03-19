package com.sample.sfms.model.report.reportSemester;

import java.util.ArrayList;
import java.util.List;

public class ReportSemesterModel {
    private String type;
    private String semester;
    private String target;
    private List<CriteriaReportModel> criteriaReportModelList;
    private double averageSemPoint;

    public ReportSemesterModel() {
        criteriaReportModelList = new ArrayList<>();
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public List<CriteriaReportModel> getCriteriaReportModelList() {
        return criteriaReportModelList;
    }

    public void setCriteriaReportModelList(List<CriteriaReportModel> criteriaReportModelList) {
        this.criteriaReportModelList = criteriaReportModelList;
    }

    public double getAverageSemPoint() {
        return averageSemPoint;
    }

    public void setAverageSemPoint(double averageSemPoint) {
        this.averageSemPoint = averageSemPoint;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
