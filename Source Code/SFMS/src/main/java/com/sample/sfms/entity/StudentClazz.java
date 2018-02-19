package com.sample.sfms.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by MyPC on 19/02/2018.
 */
@Entity
@Table(name = "student_clazz", schema = "capstone", catalog = "")
public class StudentClazz {
    private User userByUserId;
    private Clazz clazzByClazzId;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    public User getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(User userByUserId) {
        this.userByUserId = userByUserId;
    }

    @ManyToOne
    @JoinColumn(name = "clazz_id", referencedColumnName = "id", nullable = false)
    public Clazz getClazzByClazzId() {
        return clazzByClazzId;
    }

    public void setClazzByClazzId(Clazz clazzByClazzId) {
        this.clazzByClazzId = clazzByClazzId;
    }
}
