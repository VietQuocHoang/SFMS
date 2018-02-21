package com.sample.sfms.entity;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by MyPC on 21/02/2018.
 */
@Entity
public class Department {
    private int id;
    private String name;
    private Collection<Feedback> feedbacksById;
    private Collection<User> usersById;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Department that = (Department) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "departmentByDepartmentId")
    public Collection<Feedback> getFeedbacksById() {
        return feedbacksById;
    }

    public void setFeedbacksById(Collection<Feedback> feedbacksById) {
        this.feedbacksById = feedbacksById;
    }

    @OneToMany(mappedBy = "departmentByDepartmentId")
    public Collection<User> getUsersById() {
        return usersById;
    }

    public void setUsersById(Collection<User> usersById) {
        this.usersById = usersById;
    }
}
