package com.sample.sfms.entity;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Binh Nguyen on 05-Feb-18.
 */
@Entity
@Table(name = "class", schema = "capstone", catalog = "")
public class Clazz {
    private int id;
    private int semesterId;
    private int lecturerId;
    private int courseId;
    private Date startDate;
    private Date endDate;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "semesterID")
    public int getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(int semesterId) {
        this.semesterId = semesterId;
    }

    @Basic
    @Column(name = "lecturerID")
    public int getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(int lecturerId) {
        this.lecturerId = lecturerId;
    }

    @Basic
    @Column(name = "courseID")
    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @Basic
    @Column(name = "startDate")
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Column(name = "endDate")
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Clazz clazz = (Clazz) o;

        if (id != clazz.id) return false;
        if (semesterId != clazz.semesterId) return false;
        if (lecturerId != clazz.lecturerId) return false;
        if (courseId != clazz.courseId) return false;
        if (startDate != null ? !startDate.equals(clazz.startDate) : clazz.startDate != null) return false;
        if (endDate != null ? !endDate.equals(clazz.endDate) : clazz.endDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + semesterId;
        result = 31 * result + lecturerId;
        result = 31 * result + courseId;
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        return result;
    }
}
