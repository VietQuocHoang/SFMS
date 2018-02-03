package com.sample.sfms.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

/**
 * Created by Binh Nguyen on 04-Feb-18.
 */
@Entity
public class Info {
    private int id;
    private Date semester;
    private Integer lecturerId;
    private Integer courseId;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "semester")
    public Date getSemester() {
        return semester;
    }

    public void setSemester(Date semester) {
        this.semester = semester;
    }

    @Basic
    @Column(name = "lecturerID")
    public Integer getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(Integer lecturerId) {
        this.lecturerId = lecturerId;
    }

    @Basic
    @Column(name = "courseID")
    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Info info = (Info) o;

        if (id != info.id) return false;
        if (semester != null ? !semester.equals(info.semester) : info.semester != null) return false;
        if (lecturerId != null ? !lecturerId.equals(info.lecturerId) : info.lecturerId != null) return false;
        if (courseId != null ? !courseId.equals(info.courseId) : info.courseId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (semester != null ? semester.hashCode() : 0);
        result = 31 * result + (lecturerId != null ? lecturerId.hashCode() : 0);
        result = 31 * result + (courseId != null ? courseId.hashCode() : 0);
        return result;
    }
}
