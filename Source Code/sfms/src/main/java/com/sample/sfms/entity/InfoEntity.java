package com.sample.sfms.entity;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Binh Nguyen on 03-Feb-18.
 */
@Entity
@Table(name = "info", schema = "capstone", catalog = "")
public class InfoEntity {
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

        InfoEntity that = (InfoEntity) o;

        if (id != that.id) return false;
        if (semester != null ? !semester.equals(that.semester) : that.semester != null) return false;
        if (lecturerId != null ? !lecturerId.equals(that.lecturerId) : that.lecturerId != null) return false;
        if (courseId != null ? !courseId.equals(that.courseId) : that.courseId != null) return false;

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
