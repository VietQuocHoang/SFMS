package com.sample.sfms.model.feedback;

import com.sample.sfms.entity.Course;
import com.sample.sfms.entity.Department;
import com.sample.sfms.entity.Major;
import com.sample.sfms.model.report.reportList.ReportLecturerCourse;

import java.util.ArrayList;
import java.util.List;

public class FeedbackTargetWrapper {
    private List<Course> courses;
    private List<ReportLecturerCourse> lecturerCourseLists;
    private List<Major> majors;
    private List<Department> departments;

    public FeedbackTargetWrapper() {
        courses = new ArrayList<>();
        lecturerCourseLists = new ArrayList<>();
        majors = new ArrayList<>();
        departments = new ArrayList<>();
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public List<ReportLecturerCourse> getLecturerCourseLists() {
        return lecturerCourseLists;
    }

    public void setLecturerCourseLists(List<ReportLecturerCourse> lecturerCourseLists) {
        this.lecturerCourseLists = lecturerCourseLists;
    }

    public List<Major> getMajors() {
        return majors;
    }

    public void setMajors(List<Major> majors) {
        this.majors = majors;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }
}
