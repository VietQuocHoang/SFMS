package com.sample.sfms.model;

import java.util.List;

public class FeedbackReporStatistictModel {
    private int id;
    private List<FeedbackReportModel> data;

    public FeedbackReporStatistictModel(int id, List<FeedbackReportModel> data) {
        this.id = id;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<FeedbackReportModel> getData() {
        return data;
    }

    public void setData(List<FeedbackReportModel> data) {
        this.data = data;
    }
}
