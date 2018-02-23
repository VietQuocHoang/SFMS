package com.sample.sfms.model;

import com.sample.sfms.entity.*;

import java.util.List;

/**
 * Created by MyPC on 23/02/2018.
 */
public class FeedbackDetailsModel {
    private List<User> conductors;
    private List<User> reportviewers;
    private int targetedId;
    private Type type;

    public FeedbackDetailsModel() {
    }

    public FeedbackDetailsModel(List<User> conductors, List<User> reportviewers, int targetId, Type type) {
        this.conductors = conductors;
        this.reportviewers = reportviewers;
        this.targetedId = targetedId;
        this.type = type;
    }

    public List<User> getConductors() {
        return conductors;
    }

    public void setConductors(List<User> conductors) {
        this.conductors = conductors;
    }

    public List<User> getReportviewers() {
        return reportviewers;
    }

    public void setReportviewers(List<User> reportviewers) {
        this.reportviewers = reportviewers;
    }

    public int getTargetedId() {
        return targetedId;
    }

    public void setTargetedId(int targetedId) {
        this.targetedId = targetedId;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
