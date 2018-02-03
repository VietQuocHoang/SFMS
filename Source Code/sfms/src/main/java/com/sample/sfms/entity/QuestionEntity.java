package com.sample.sfms.entity;

import javax.persistence.*;

/**
 * Created by Binh Nguyen on 03-Feb-18.
 */
@Entity
@Table(name = "question", schema = "capstone", catalog = "")
public class QuestionEntity {
    private int id;
    private String type;
    private String questionContent;
    private String suggestion;
    private Byte isRequied;
    private Integer criteriaId;
    private Integer feedbackId;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "questionContent")
    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    @Basic
    @Column(name = "suggestion")
    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    @Basic
    @Column(name = "isRequied")
    public Byte getIsRequied() {
        return isRequied;
    }

    public void setIsRequied(Byte isRequied) {
        this.isRequied = isRequied;
    }

    @Basic
    @Column(name = "criteriaID")
    public Integer getCriteriaId() {
        return criteriaId;
    }

    public void setCriteriaId(Integer criteriaId) {
        this.criteriaId = criteriaId;
    }

    @Basic
    @Column(name = "feedbackID")
    public Integer getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(Integer feedbackId) {
        this.feedbackId = feedbackId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuestionEntity that = (QuestionEntity) o;

        if (id != that.id) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (questionContent != null ? !questionContent.equals(that.questionContent) : that.questionContent != null)
            return false;
        if (suggestion != null ? !suggestion.equals(that.suggestion) : that.suggestion != null) return false;
        if (isRequied != null ? !isRequied.equals(that.isRequied) : that.isRequied != null) return false;
        if (criteriaId != null ? !criteriaId.equals(that.criteriaId) : that.criteriaId != null) return false;
        if (feedbackId != null ? !feedbackId.equals(that.feedbackId) : that.feedbackId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (questionContent != null ? questionContent.hashCode() : 0);
        result = 31 * result + (suggestion != null ? suggestion.hashCode() : 0);
        result = 31 * result + (isRequied != null ? isRequied.hashCode() : 0);
        result = 31 * result + (criteriaId != null ? criteriaId.hashCode() : 0);
        result = 31 * result + (feedbackId != null ? feedbackId.hashCode() : 0);
        return result;
    }
}
