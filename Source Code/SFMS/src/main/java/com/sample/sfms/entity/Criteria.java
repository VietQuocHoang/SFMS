package com.sample.sfms.entity;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by MyPC on 22/02/2018.
 */
@Entity
public class Criteria {
    private int id;
    private String criteria;
    private Collection<Question> questionsById;

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
    @Column(name = "criteria", nullable = false, length = 45)
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

    @OneToMany(mappedBy = "criteriaByCriteriaId")
    public Collection<Question> getQuestionsById() {
        return questionsById;
    }

    public void setQuestionsById(Collection<Question> questionsById) {
        this.questionsById = questionsById;
    }
}
