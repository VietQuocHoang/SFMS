package com.sample.sfms.entity;

import javax.persistence.*;

/**
 * Created by MyPC on 19/02/2018.
 */
@Entity
@Table(name = "major_course", schema = "capstone")
@IdClass(MajorCoursePK.class)
public class MajorCourse {
    private int majorId;
    private int courseId;
    private Major majorByMajorId;
    private Course courseByCourseId;

    @Id
    @Column(name = "major_id", insertable = false, updatable = false)
    public int getMajorId() {
        return majorId;
    }

    public void setMajorId(int majorId) {
        this.majorId = majorId;
    }

    @Id
    @Column(name = "course_id", insertable = false, updatable = false)
    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MajorCourse that = (MajorCourse) o;

        if (majorId != that.majorId) return false;
        if (courseId != that.courseId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = majorId;
        result = 31 * result + courseId;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "major_id", referencedColumnName = "id", nullable = false)
    public Major getMajorByMajorId() {
        return majorByMajorId;
    }

    public void setMajorByMajorId(Major majorByMajorId) {
        this.majorByMajorId = majorByMajorId;
    }

    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "id", nullable = false)
    public Course getCourseByCourseId() {
        return courseByCourseId;
    }

    public void setCourseByCourseId(Course courseByCourseId) {
        this.courseByCourseId = courseByCourseId;
    }
}
