package com.sample.sfms.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by MyPC on 19/02/2018.
 */
public class ClazzCoursePK implements Serializable {
    private int clazzId;
    private int courseId;

    @Column(name = "clazz_id")
    @Id
    public int getClazzId() {
        return clazzId;
    }

    public void setClazzId(int clazzId) {
        this.clazzId = clazzId;
    }

    @Column(name = "course_id")
    @Id
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

        ClazzCoursePK that = (ClazzCoursePK) o;

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
}
