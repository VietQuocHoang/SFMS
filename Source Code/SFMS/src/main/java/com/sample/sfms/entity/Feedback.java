package com.sample.sfms.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * Created by Binh Nguyen on 04-Feb-18.
 */
@Entity
public class Feedback {
    private int id;
    private Integer point;
    private Integer preferenceId;
    private Byte isTemplate;
    private Timestamp createDate;
    private Timestamp fromDate;
    private Timestamp endDate;
    private String templateName;
    private String feedbackName;
    private String templateDes;
    private String feedbackDes;
    private Byte isPublished;
    private Integer questionId;
    private Integer userId;
    private Integer departmentId;
    private Integer courseId;
    private Integer lecturerId;
    private Integer majorId;
    private Integer infoId;
    private Integer typeId;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "point")
    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    @Basic
    @Column(name = "preferenceID")
    public Integer getPreferenceId() {
        return preferenceId;
    }

    public void setPreferenceId(Integer preferenceId) {
        this.preferenceId = preferenceId;
    }

    @Basic
    @Column(name = "isTemplate")
    public Byte getIsTemplate() {
        return isTemplate;
    }

    public void setIsTemplate(Byte isTemplate) {
        this.isTemplate = isTemplate;
    }

    @Basic
    @Column(name = "createDate")
    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "fromDate")
    public Timestamp getFromDate() {
        return fromDate;
    }

    public void setFromDate(Timestamp fromDate) {
        this.fromDate = fromDate;
    }

    @Basic
    @Column(name = "endDate")
    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    @Basic
    @Column(name = "templateName")
    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    @Basic
    @Column(name = "feedbackName")
    public String getFeedbackName() {
        return feedbackName;
    }

    public void setFeedbackName(String feedbackName) {
        this.feedbackName = feedbackName;
    }

    @Basic
    @Column(name = "templateDes")
    public String getTemplateDes() {
        return templateDes;
    }

    public void setTemplateDes(String templateDes) {
        this.templateDes = templateDes;
    }

    @Basic
    @Column(name = "feedbackDes")
    public String getFeedbackDes() {
        return feedbackDes;
    }

    public void setFeedbackDes(String feedbackDes) {
        this.feedbackDes = feedbackDes;
    }

    @Basic
    @Column(name = "isPublished")
    public Byte getIsPublished() {
        return isPublished;
    }

    public void setIsPublished(Byte isPublished) {
        this.isPublished = isPublished;
    }

    @Basic
    @Column(name = "questionID")
    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    @Basic
    @Column(name = "userID")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "departmentID")
    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    @Basic
    @Column(name = "courseID")
    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    @Basic
    @Column(name = "lecturerID")
    public Integer getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(Integer lecturerId) {
        this.lecturerId = lecturerId;
    }

    @Basic
    @Column(name = "majorID")
    public Integer getMajorId() {
        return majorId;
    }

    public void setMajorId(Integer majorId) {
        this.majorId = majorId;
    }

    @Basic
    @Column(name = "infoID")
    public Integer getInfoId() {
        return infoId;
    }

    public void setInfoId(Integer infoId) {
        this.infoId = infoId;
    }

    @Basic
    @Column(name = "typeID")
    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Feedback feedback = (Feedback) o;

        if (id != feedback.id) return false;
        if (point != null ? !point.equals(feedback.point) : feedback.point != null) return false;
        if (preferenceId != null ? !preferenceId.equals(feedback.preferenceId) : feedback.preferenceId != null)
            return false;
        if (isTemplate != null ? !isTemplate.equals(feedback.isTemplate) : feedback.isTemplate != null) return false;
        if (createDate != null ? !createDate.equals(feedback.createDate) : feedback.createDate != null) return false;
        if (fromDate != null ? !fromDate.equals(feedback.fromDate) : feedback.fromDate != null) return false;
        if (endDate != null ? !endDate.equals(feedback.endDate) : feedback.endDate != null) return false;
        if (templateName != null ? !templateName.equals(feedback.templateName) : feedback.templateName != null)
            return false;
        if (feedbackName != null ? !feedbackName.equals(feedback.feedbackName) : feedback.feedbackName != null)
            return false;
        if (templateDes != null ? !templateDes.equals(feedback.templateDes) : feedback.templateDes != null)
            return false;
        if (feedbackDes != null ? !feedbackDes.equals(feedback.feedbackDes) : feedback.feedbackDes != null)
            return false;
        if (isPublished != null ? !isPublished.equals(feedback.isPublished) : feedback.isPublished != null)
            return false;
        if (questionId != null ? !questionId.equals(feedback.questionId) : feedback.questionId != null) return false;
        if (userId != null ? !userId.equals(feedback.userId) : feedback.userId != null) return false;
        if (departmentId != null ? !departmentId.equals(feedback.departmentId) : feedback.departmentId != null)
            return false;
        if (courseId != null ? !courseId.equals(feedback.courseId) : feedback.courseId != null) return false;
        if (lecturerId != null ? !lecturerId.equals(feedback.lecturerId) : feedback.lecturerId != null) return false;
        if (majorId != null ? !majorId.equals(feedback.majorId) : feedback.majorId != null) return false;
        if (infoId != null ? !infoId.equals(feedback.infoId) : feedback.infoId != null) return false;
        if (typeId != null ? !typeId.equals(feedback.typeId) : feedback.typeId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (point != null ? point.hashCode() : 0);
        result = 31 * result + (preferenceId != null ? preferenceId.hashCode() : 0);
        result = 31 * result + (isTemplate != null ? isTemplate.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (fromDate != null ? fromDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (templateName != null ? templateName.hashCode() : 0);
        result = 31 * result + (feedbackName != null ? feedbackName.hashCode() : 0);
        result = 31 * result + (templateDes != null ? templateDes.hashCode() : 0);
        result = 31 * result + (feedbackDes != null ? feedbackDes.hashCode() : 0);
        result = 31 * result + (isPublished != null ? isPublished.hashCode() : 0);
        result = 31 * result + (questionId != null ? questionId.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (departmentId != null ? departmentId.hashCode() : 0);
        result = 31 * result + (courseId != null ? courseId.hashCode() : 0);
        result = 31 * result + (lecturerId != null ? lecturerId.hashCode() : 0);
        result = 31 * result + (majorId != null ? majorId.hashCode() : 0);
        result = 31 * result + (infoId != null ? infoId.hashCode() : 0);
        result = 31 * result + (typeId != null ? typeId.hashCode() : 0);
        return result;
    }
}
