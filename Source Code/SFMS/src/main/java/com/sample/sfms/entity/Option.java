package com.sample.sfms.entity;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by MyPC on 21/02/2018.
 */
@Entity
public class Option {
    private int id;
    private String optionContent;
    private Double point;
    private Collection<Answer> answersById;
    private Question questionByQuestionId;

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
    @Column(name = "option_content", nullable = true, length = 50)
    public String getOptionContent() {
        return optionContent;
    }

    public void setOptionContent(String optionContent) {
        this.optionContent = optionContent;
    }

    @Basic
    @Column(name = "point", nullable = true, precision = 0)
    public Double getPoint() {
        return point;
    }

    public void setPoint(Double point) {
        this.point = point;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Option option = (Option) o;

        if (id != option.id) return false;
        if (optionContent != null ? !optionContent.equals(option.optionContent) : option.optionContent != null)
            return false;
        if (point != null ? !point.equals(option.point) : option.point != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (optionContent != null ? optionContent.hashCode() : 0);
        result = 31 * result + (point != null ? point.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "optionByOptionId")
    public Collection<Answer> getAnswersById() {
        return answersById;
    }

    public void setAnswersById(Collection<Answer> answersById) {
        this.answersById = answersById;
    }

    @ManyToOne
    @JoinColumn(name = "question_id", referencedColumnName = "id")
    public Question getQuestionByQuestionId() {
        return questionByQuestionId;
    }

    public void setQuestionByQuestionId(Question questionByQuestionId) {
        this.questionByQuestionId = questionByQuestionId;
    }
}
