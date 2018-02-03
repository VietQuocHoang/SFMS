package com.sample.sfms.entity;

import javax.persistence.*;

/**
 * Created by Binh Nguyen on 03-Feb-18.
 */
@Entity
@Table(name = "option", schema = "capstone", catalog = "")
public class OptionEntity {
    private int id;
    private String optionContent;
    private Double point;
    private Integer questionId;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "optionContent")
    public String getOptionContent() {
        return optionContent;
    }

    public void setOptionContent(String optionContent) {
        this.optionContent = optionContent;
    }

    @Basic
    @Column(name = "point")
    public Double getPoint() {
        return point;
    }

    public void setPoint(Double point) {
        this.point = point;
    }

    @Basic
    @Column(name = "questionID")
    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OptionEntity that = (OptionEntity) o;

        if (id != that.id) return false;
        if (optionContent != null ? !optionContent.equals(that.optionContent) : that.optionContent != null)
            return false;
        if (point != null ? !point.equals(that.point) : that.point != null) return false;
        if (questionId != null ? !questionId.equals(that.questionId) : that.questionId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (optionContent != null ? optionContent.hashCode() : 0);
        result = 31 * result + (point != null ? point.hashCode() : 0);
        result = 31 * result + (questionId != null ? questionId.hashCode() : 0);
        return result;
    }
}
