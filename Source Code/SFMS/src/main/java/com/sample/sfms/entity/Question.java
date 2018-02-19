package com.sample.sfms.entity;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by MyPC on 19/02/2018.
 */
@Entity
public class Question {
    private int id;
    private String type;
    private String suggestion;
    private Integer criteriaId;
    private int feedbackId;
    private byte isRequied;
    private String questionContent;
    private Collection<Option> optionsById;
    private Criteria criteriaByCriteriaId;
    private Feedback feedbackByFeedbackId;

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
    @Column(name = "suggestion")
    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    @Basic
    @Column(name = "criteria_id")
    public Integer getCriteriaId() {
        return criteriaId;
    }

    public void setCriteriaId(Integer criteriaId) {
        this.criteriaId = criteriaId;
    }

    @Basic
    @Column(name = "feedback_id")
    public int getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }

    @Basic
    @Column(name = "is_requied")
    public byte getIsRequied() {
        return isRequied;
    }

    public void setIsRequied(byte isRequied) {
        this.isRequied = isRequied;
    }

    @Basic
    @Column(name = "question_content")
    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Question question = (Question) o;

        if (id != question.id) return false;
        if (feedbackId != question.feedbackId) return false;
        if (isRequied != question.isRequied) return false;
        if (type != null ? !type.equals(question.type) : question.type != null) return false;
        if (suggestion != null ? !suggestion.equals(question.suggestion) : question.suggestion != null) return false;
        if (criteriaId != null ? !criteriaId.equals(question.criteriaId) : question.criteriaId != null) return false;
        if (questionContent != null ? !questionContent.equals(question.questionContent) : question.questionContent != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (suggestion != null ? suggestion.hashCode() : 0);
        result = 31 * result + (criteriaId != null ? criteriaId.hashCode() : 0);
        result = 31 * result + feedbackId;
        result = 31 * result + (int) isRequied;
        result = 31 * result + (questionContent != null ? questionContent.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "questionByQuestionId")
    public Collection<Option> getOptionsById() {
        return optionsById;
    }

    public void setOptionsById(Collection<Option> optionsById) {
        this.optionsById = optionsById;
    }

    @ManyToOne
    @JoinColumn(name = "criteria_id", referencedColumnName = "id")
    public Criteria getCriteriaByCriteriaId() {
        return criteriaByCriteriaId;
    }

    public void setCriteriaByCriteriaId(Criteria criteriaByCriteriaId) {
        this.criteriaByCriteriaId = criteriaByCriteriaId;
    }

    @ManyToOne
    @JoinColumn(name = "feedback_id", referencedColumnName = "id", nullable = false)
    public Feedback getFeedbackByFeedbackId() {
        return feedbackByFeedbackId;
    }

    public void setFeedbackByFeedbackId(Feedback feedbackByFeedbackId) {
        this.feedbackByFeedbackId = feedbackByFeedbackId;
    }
}
