package com.sample.sfms.repository;

import com.sample.sfms.entity.Feedback;
import com.sample.sfms.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by MyPC on 20/02/2018.
 */
@org.springframework.stereotype.Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {

    @Query("select q from Question q where " +
            "q.feedbackByFeedbackId.id = :feedbackID")
    List<Question> findByFeedbackId(@Param("feedbackID")int feedbackID);

}
