package com.sample.sfms.model;

public class FeedbackReportModel {
    private String criteria;
    private double sum;
    private double count;

    public FeedbackReportModel() {
    }

    public FeedbackReportModel(String criteria, double sum, double count) {
        this.criteria = criteria;
        this.sum = sum;
        this.count = count;
    }

    public String getCriteria() {
        return criteria;
    }

    public void setCriteria(String criteria) {
        this.criteria = criteria;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public void addSum(double sum) {
        this.sum += sum;
    }

    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }

    public void addCount(double count) {
        this.count += count;
    }
}
