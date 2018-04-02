package com.sample.sfms.model.dashboard;

import com.sample.sfms.entity.UserFeedback;

import java.util.ArrayList;
import java.util.List;

public class DashboardWrapperModel {
    private List<UserFeedback> feedbackList;
    private List<PieChartModel> pieCharts;

    public DashboardWrapperModel() {
        pieCharts = new ArrayList<>();
    }

    public void addPieChart(PieChartModel pieChart) {
        pieCharts.add(pieChart);
    }

    public List<UserFeedback> getFeedbackList() {
        return feedbackList;
    }


    public void setFeedbackList(List<UserFeedback> feedbackList) {
        this.feedbackList = feedbackList;
    }


    public List<PieChartModel> getPieCharts() {
        return pieCharts;
    }

    public void setPieCharts(List<PieChartModel> pieCharts) {
        this.pieCharts = pieCharts;
    }
}
