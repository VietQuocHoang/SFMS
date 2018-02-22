package com.sample.sfms.model;

import com.sample.sfms.entity.*;

import java.util.List;

/**
 * Created by MyPC on 23/02/2018.
 */
public class FeedbackDetailsModel {
    private List<User> conductors;
    private List<User> reportviewers;
    private Major targetedMajor;
    private Course targetedCourse;
    private Clazz targetedClazz;
    private Department targetedDepartment;
    private Type type;

    public FeedbackDetailsModel() {
    }

    public FeedbackDetailsModel(List<User> conductors, List<User> reportviewers, Major targetedMajor, Course targetedCourse, Clazz targetedClazz, Department targetedDepartment, Type type) {
        this.conductors = conductors;
        this.reportviewers = reportviewers;
        this.targetedMajor = targetedMajor;
        this.targetedCourse = targetedCourse;
        this.targetedClazz = targetedClazz;
        this.targetedDepartment = targetedDepartment;
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

    public Major getTargetedMajor() {
        return targetedMajor;
    }

    public void setTargetedMajor(Major targetedMajor) {
        this.targetedMajor = targetedMajor;
    }

    public Course getTargetedCourse() {
        return targetedCourse;
    }

    public void setTargetedCourse(Course targetedCourse) {
        this.targetedCourse = targetedCourse;
    }

    public Clazz getTargetedClazz() {
        return targetedClazz;
    }

    public void setTargetedClazz(Clazz targetedClazz) {
        this.targetedClazz = targetedClazz;
    }

    public Department getTargetedDepartment() {
        return targetedDepartment;
    }

    public void setTargetedDepartment(Department targetedDepartment) {
        this.targetedDepartment = targetedDepartment;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
