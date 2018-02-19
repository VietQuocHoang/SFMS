package com.sample.sfms.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by MyPC on 19/02/2018.
 */
@Entity
@Table(name = "clazz_course", schema = "capstone", catalog = "")
public class ClazzCourse {
    private Clazz clazzByClazzId;
    private Course courseByCourseId;

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
