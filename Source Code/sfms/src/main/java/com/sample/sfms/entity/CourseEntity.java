package com.sample.sfms.entity;

import javax.persistence.*;

/**
 * Created by Binh Nguyen on 03-Feb-18.
 */
@Entity
@Table(name = "course", schema = "capstone", catalog = "")
public class CourseEntity {
    private int id;
    private String name;
    private Integer majorId;

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
    @Column(name = "majorID")
    public Integer getMajorId() {
        return majorId;
    }

    public void setMajorId(Integer majorId) {
        this.majorId = majorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CourseEntity that = (CourseEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (majorId != null ? !majorId.equals(that.majorId) : that.majorId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (majorId != null ? majorId.hashCode() : 0);
        return result;
    }
}
