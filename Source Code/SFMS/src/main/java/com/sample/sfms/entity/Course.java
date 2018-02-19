package com.sample.sfms.entity;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by MyPC on 19/02/2018.
 */
@Entity
public class Course {
    private int id;
    private String name;
    private String code;
    private Collection<ClazzCourse> clazzCoursesById;
    private Collection<Feedback> feedbacksById;
    private Collection<MajorCourse> majorCoursesById;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Course course = (Course) o;

        if (id != course.id) return false;
        if (name != null ? !name.equals(course.name) : course.name != null) return false;
        if (code != null ? !code.equals(course.code) : course.code != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "courseByCourseId")
    public Collection<ClazzCourse> getClazzCoursesById() {
        return clazzCoursesById;
    }

    public void setClazzCoursesById(Collection<ClazzCourse> clazzCoursesById) {
        this.clazzCoursesById = clazzCoursesById;
    }

    @OneToMany(mappedBy = "courseByCourseId")
    public Collection<Feedback> getFeedbacksById() {
        return feedbacksById;
    }

    public void setFeedbacksById(Collection<Feedback> feedbacksById) {
        this.feedbacksById = feedbacksById;
    }

    @OneToMany(mappedBy = "courseByCourseId")
    public Collection<MajorCourse> getMajorCoursesById() {
        return majorCoursesById;
    }

    public void setMajorCoursesById(Collection<MajorCourse> majorCoursesById) {
        this.majorCoursesById = majorCoursesById;
    }
}
