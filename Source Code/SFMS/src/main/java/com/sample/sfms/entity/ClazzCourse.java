package com.sample.sfms.entity;

import javax.persistence.*;

/**
 * Created by MyPC on 19/02/2018.
 */
@Entity
@Table(name = "clazz_course", schema = "capstone")
@IdClass(ClazzCoursePK.class)
public class ClazzCourse {
    private int clazzId;
    private int courseId;
    private Clazz clazzByClazzId;
    private Course courseByCourseId;

    @Id
    @Column(name = "clazz_id", insertable = false, updatable = false)
    public int getClazzId() {
        return clazzId;
    }

    public void setClazzId(int clazzId) {
        this.clazzId = clazzId;
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

        ClazzCourse that = (ClazzCourse) o;

        if (clazzId != that.clazzId) return false;
        if (courseId != that.courseId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = clazzId;
        result = 31 * result + courseId;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "clazz_id", referencedColumnName = "id", nullable = false)
    public Clazz getClazzByClazzId() {
        return clazzByClazzId;
    }

    public void setClazzByClazzId(Clazz clazzByClazzId) {
        this.clazzByClazzId = clazzByClazzId;
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
