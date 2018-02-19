package com.sample.sfms.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by MyPC on 19/02/2018.
 */
@Entity
@Table(name = "major_course", schema = "capstone", catalog = "")
public class MajorCourse {
    private Major majorByMajorId;
    private Course courseByCourseId;

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
