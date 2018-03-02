package com.sample.sfms.entity;

import javax.persistence.*;

/**
 * Created by MyPC on 01/03/2018.
 */
@Entity
@Table(name = "user_feedback", schema = "capstone", catalog = "")
@IdClass(UserFeedbackPK.class)
public class UserFeedback {
    private int userId;
    private int feedbackId;
    private Byte isConductor;
    private Byte isViewer;
    private User userByUserId;
    private Feedback feedbackByFeedbackId;
    @Id
    @Column(name = "user_id", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Id
    @Column(name = "feedback_id", nullable = false)
    public int getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(int courseId) {
        this.feedbackId = courseId;
    }

    @Basic
    @Column(name = "is_conductor", nullable = true)
    public Byte getIsConductor() {
        return isConductor;
    }

    public void setIsConductor(Byte isConductor) {
        this.isConductor = isConductor;
    }

    @Basic
    @Column(name = "is_viewer", nullable = true)
    public Byte getIsViewer() {
        return isViewer;
    }

    public void setIsViewer(Byte isViewer) {
        this.isViewer = isViewer;
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserFeedback that = (UserFeedback) o;

        if (userId != that.userId) return false;
        if (feedbackId != that.feedbackId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + feedbackId;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    public User getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(User userByUserId) {
        this.userByUserId = userByUserId;
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
