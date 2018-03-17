package com.sample.sfms.model.feedback;

import com.sample.sfms.entity.Clazz;
import com.sample.sfms.entity.Course;
import com.sample.sfms.entity.Department;
import com.sample.sfms.entity.Major;

import java.util.ArrayList;
import java.util.List;

public class FeedbackTargetWrapper {
    private List<Course> courses;
    private List<Clazz> classes;
    private List<Major> majors;
    private List<Department> departments;

    public FeedbackTargetWrapper() {
        courses = new ArrayList<>();
        classes = new ArrayList<>();
        majors = new ArrayList<>();
        departments = new ArrayList<>();
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public List<Clazz> getClasses() {
        return classes;
    }

    public void setClasses(List<Clazz> classes) {
        this.classes = classes;
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
