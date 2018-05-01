package com.sample.sfms.model.report.reportList;

public class ReportLecturerCourse {
    private final int LECTURER_ID = 0;
    private final int FULL_NAME = 1;
    private final int COURSE_ID = 2;
    private final int COURSE_NAME = 3;
    private final int COURSE_CODE = 4;

    private int lecturerId;
    private int courseId;
    private String fullname;
    private String courseName;
    private String code;

    public ReportLecturerCourse() {
    }

    public void mapFromObject(Object[] objects) {
        setLecturerId(Integer.parseInt(objects[LECTURER_ID].toString()));
        setFullname(objects[FULL_NAME].toString());
        setCourseId(Integer.parseInt(objects[COURSE_ID].toString()));
        setCourseName(objects[COURSE_NAME].toString());
        setCode(objects[COURSE_CODE].toString());
    }

    public int getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(int lecturerId) {
        this.lecturerId = lecturerId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}