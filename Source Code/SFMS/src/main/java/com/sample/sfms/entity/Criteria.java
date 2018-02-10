package com.sample.sfms.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Binh Nguyen on 05-Feb-18.
 */
@Entity
public class Criteria {
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

        Criteria criteria1 = (Criteria) o;

        if (id != criteria1.id) return false;
        if (criteria != null ? !criteria.equals(criteria1.criteria) : criteria1.criteria != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (criteria != null ? criteria.hashCode() : 0);
        return result;
    }
}