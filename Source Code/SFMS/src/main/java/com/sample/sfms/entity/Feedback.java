package com.sample.sfms.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * Created by MyPC on 22/02/2018.
 */
@Entity
public class Feedback {
    private int id;
    private Integer point;
    private byte isTemplate;
    private Timestamp createDate;
    private Timestamp startDate;
    private Timestamp endDate;
    private String feedbackDes;
    private String feedbackName;
    private String templateDes;
    private String templateName;
    private Byte isPublished;
    private String conductors;
    private String viewers;
    private User userByCreatorId;
    private Department departmentByDepartmentId;
    private Course courseByCourseId;
    private Major majorByMajorId;
    private Clazz clazzByClazzId;
    private Type typeByTypeId;
    private Feedback feedbackByReferenceId;
    private Semester semesterBySemesterId;
    private Collection<Feedback> feedbacksById;
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
    @Column(name = "point", nullable = true)
    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    @Basic
    @Column(name = "is_template", nullable = false)
    public byte getIsTemplate() {
        return isTemplate;
    }

    public void setIsTemplate(byte isTemplate) {
        this.isTemplate = isTemplate;
    }

    @Basic
    @Column(name = "create_date", nullable = true)
    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "start_date", nullable = true)
    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Column(name = "end_date", nullable = true)
    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    @Basic
    @Column(name = "feedback_des", nullable = true, length = 255)
    public String getFeedbackDes() {
        return feedbackDes;
    }

    public void setFeedbackDes(String feedbackDes) {
        this.feedbackDes = feedbackDes;
    }

    @Basic
    @Column(name = "feedback_name", nullable = true, length = 255)
    public String getFeedbackName() {
        return feedbackName;
    }

    public void setFeedbackName(String feedbackName) {
        this.feedbackName = feedbackName;
    }

    @Basic
    @Column(name = "template_des", nullable = true, length = 255)
    public String getTemplateDes() {
        return templateDes;
    }

    public void setTemplateDes(String templateDes) {
        this.templateDes = templateDes;
    }

    @Basic
    @Column(name = "template_name", nullable = true, length = 255)
    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    @Basic
    @Column(name = "is_published", nullable = true)
    public Byte getIsPublished() {
        return isPublished;
    }

    public void setIsPublished(Byte isPublished) {
        this.isPublished = isPublished;
    }

    @Basic
    @Column(name = "conductors", nullable = true, length = -1)
    public String getConductors() {
        return conductors;
    }

    public void setConductors(String conductors) {
        this.conductors = conductors;
    }

    @Basic
    @Column(name = "viewers", nullable = true, length = -1)
    public String getViewers() {
        return viewers;
    }

    public void setViewers(String viewers) {
        this.viewers = viewers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Feedback feedback = (Feedback) o;

        if (id != feedback.id) return false;
        if (isTemplate != feedback.isTemplate) return false;
        if (point != null ? !point.equals(feedback.point) : feedback.point != null) return false;
        if (createDate != null ? !createDate.equals(feedback.createDate) : feedback.createDate != null) return false;
        if (startDate != null ? !startDate.equals(feedback.startDate) : feedback.startDate != null) return false;
        if (endDate != null ? !endDate.equals(feedback.endDate) : feedback.endDate != null) return false;
        if (feedbackDes != null ? !feedbackDes.equals(feedback.feedbackDes) : feedback.feedbackDes != null)
            return false;
        if (feedbackName != null ? !feedbackName.equals(feedback.feedbackName) : feedback.feedbackName != null)
            return false;
        if (templateDes != null ? !templateDes.equals(feedback.templateDes) : feedback.templateDes != null)
            return false;
        if (templateName != null ? !templateName.equals(feedback.templateName) : feedback.templateName != null)
            return false;
        if (isPublished != null ? !isPublished.equals(feedback.isPublished) : feedback.isPublished != null)
            return false;
        if (conductors != null ? !conductors.equals(feedback.conductors) : feedback.conductors != null) return false;
        if (viewers != null ? !viewers.equals(feedback.viewers) : feedback.viewers != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (point != null ? point.hashCode() : 0);
        result = 31 * result + (int) isTemplate;
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (feedbackDes != null ? feedbackDes.hashCode() : 0);
        result = 31 * result + (feedbackName != null ? feedbackName.hashCode() : 0);
        result = 31 * result + (templateDes != null ? templateDes.hashCode() : 0);
        result = 31 * result + (templateName != null ? templateName.hashCode() : 0);
        result = 31 * result + (isPublished != null ? isPublished.hashCode() : 0);
        result = 31 * result + (conductors != null ? conductors.hashCode() : 0);
        result = 31 * result + (viewers != null ? viewers.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "creator_id", referencedColumnName = "id")
    public User getUserByCreatorId() {
        return userByCreatorId;
    }

    public void setUserByCreatorId(User userByCreatorId) {
        this.userByCreatorId = userByCreatorId;
    }

    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    public Department getDepartmentByDepartmentId() {
        return departmentByDepartmentId;
    }

    public void setDepartmentByDepartmentId(Department departmentByDepartmentId) {
        this.departmentByDepartmentId = departmentByDepartmentId;
    }

    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    public Course getCourseByCourseId() {
        return courseByCourseId;
    }

    public void setCourseByCourseId(Course courseByCourseId) {
        this.courseByCourseId = courseByCourseId;
    }

    @ManyToOne
    @JoinColumn(name = "major_id", referencedColumnName = "id")
    public Major getMajorByMajorId() {
        return majorByMajorId;
    }

    public void setMajorByMajorId(Major majorByMajorId) {
        this.majorByMajorId = majorByMajorId;
    }

    @ManyToOne
    @JoinColumn(name = "clazz_id", referencedColumnName = "id")
    public Clazz getClazzByClazzId() {
        return clazzByClazzId;
    }

    public void setClazzByClazzId(Clazz clazzByClazzId) {
        this.clazzByClazzId = clazzByClazzId;
    }

    @ManyToOne
    @JoinColumn(name = "type_id", referencedColumnName = "id", nullable = false)
    public Type getTypeByTypeId() {
        return typeByTypeId;
    }

    public void setTypeByTypeId(Type typeByTypeId) {
        this.typeByTypeId = typeByTypeId;
    }

    @ManyToOne
    @JoinColumn(name = "reference_id", referencedColumnName = "id")
    public Feedback getFeedbackByReferenceId() {
        return feedbackByReferenceId;
    }

    public void setFeedbackByReferenceId(Feedback feedbackByReferenceId) {
        this.feedbackByReferenceId = feedbackByReferenceId;
    }

    @ManyToOne
    @JoinColumn(name = "semester_id", referencedColumnName = "id")
    public Semester getSemesterBySemesterId() {
        return semesterBySemesterId;
    }

    public void setSemesterBySemesterId(Semester semesterBySemesterId) {
        this.semesterBySemesterId = semesterBySemesterId;
    }

    @OneToMany(mappedBy = "feedbackByReferenceId")
    public Collection<Feedback> getFeedbacksById() {
        return feedbacksById;
    }

    public void setFeedbacksById(Collection<Feedback> feedbacksById) {
        this.feedbacksById = feedbacksById;
    }

    @OneToMany(mappedBy = "feedbackByFeedbackId")
    public Collection<Question> getQuestionsById() {
        return questionsById;
    }

    public void setQuestionsById(Collection<Question> questionsById) {
        this.questionsById = questionsById;
    }
}
