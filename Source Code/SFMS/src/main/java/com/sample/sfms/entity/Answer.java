package com.sample.sfms.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by MyPC on 19/02/2018.
 */
@Entity
public class Answer {
    private int id;
    private String answerContent;
    private Timestamp createDate;
    private Option optionByOptionId;
    private User userByUserId;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "answer_content")
    public String getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }

    @Basic
    @Column(name = "create_date")
    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Answer answer = (Answer) o;

        if (id != answer.id) return false;
        if (answerContent != null ? !answerContent.equals(answer.answerContent) : answer.answerContent != null)
            return false;
        if (createDate != null ? !createDate.equals(answer.createDate) : answer.createDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (answerContent != null ? answerContent.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "option_id", referencedColumnName = "id", nullable = false)
    public Option getOptionByOptionId() {
        return optionByOptionId;
    }

    public void setOptionByOptionId(Option optionByOptionId) {
        this.optionByOptionId = optionByOptionId;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    public User getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(User userByUserId) {
        this.userByUserId = userByUserId;
    }
}
