package com.sample.sfms.entity;

import javax.persistence.*;

/**
 * Created by Binh Nguyen on 03-Feb-18.
 */
@Entity
@Table(name = "criteria", schema = "capstone", catalog = "")
public class CriteriaEntity {
    private int id;
    private String criteria;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "criteria")
    public String getCriteria() {
        return criteria;
    }

    public void setCriteria(String criteria) {
        this.criteria = criteria;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CriteriaEntity that = (CriteriaEntity) o;

        if (id != that.id) return false;
        if (criteria != null ? !criteria.equals(that.criteria) : that.criteria != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (criteria != null ? criteria.hashCode() : 0);
        return result;
    }
}
