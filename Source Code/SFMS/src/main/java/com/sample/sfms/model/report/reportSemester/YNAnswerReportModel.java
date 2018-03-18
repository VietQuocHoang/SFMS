package com.sample.sfms.model.report.reportSemester;

import java.util.ArrayList;
import java.util.List;

public class YNAnswerReportModel {
    private String optionContent;
    private int selectedCount;
    private List<OpenAnswerReportModel> listOptionsOther;
    private double optionPoint;

    public YNAnswerReportModel() {
        listOptionsOther = new ArrayList<>();
    }

    public String getOptionContent() {
        return optionContent;
    }

    public void setOptionContent(String optionContent) {
        this.optionContent = optionContent;
    }

    public int getSelectedCount() {
        return selectedCount;
    }

    public void setSelectedCount(int selectedCount) {
        this.selectedCount = selectedCount;
    }

    public List<OpenAnswerReportModel> getListOptionsOther() {
        return listOptionsOther;
    }

    public void setListOptionsOther(List<OpenAnswerReportModel> listOptionsOther) {
        this.listOptionsOther = listOptionsOther;
    }

    public double getOptionPoint() {
        return optionPoint;
    }

    public void setOptionPoint(double optionPoint) {
        this.optionPoint = optionPoint;
    }
}
