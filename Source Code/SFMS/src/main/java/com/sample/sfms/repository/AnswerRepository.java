package com.sample.sfms.repository;

import com.sample.sfms.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Integer> {

    @Query(value = "SELECT Count(distinct a.user_id) " +
            "from capstone.answer a inner join capstone.optionn o on(a.option_id= o.id) inner join capstone.question q on (q.id=o.question_id) " +
            "where q.id=:questionId", nativeQuery = true)
    int countNumberOfQuestionsResponse(@Param("questionId") int questionId);

    @Query(value = "SELECT COUNT (a.id) from Answer a where a.optionnByOptionnId.id=:optionId")
    int countNumberOfTimeAnswerSelected(@Param("optionId") int optionId);

    @Query(value = "SELECT a from Answer a where a.optionnByOptionnId.id=:optionId and a.optionnByOptionnId.point=0")
    List<Answer> getListOtherOptionAnswerContent(@Param("optionId") int optionId);

    List<Answer> getAllByOptionnByOptionnIdId(int id);

    @Query(value = "select a from Answer a where a.userByUserId.id=:userId AND a.optionnByOptionnId.questionByQuestionId.feedbackByFeedbackId.id=:feedbackId")
    List<Answer> getAllAnswerByUserIdAndFeedbackId(@Param("userId") int userId, @Param("feedbackId") int feedbackId);

    @Transactional
    @Modifying
    @Query(value = "DELETE a.* " +
            "from capstone.answer a inner join capstone.optionn o on (a.option_id=o.id) " +
            "inner join capstone.question q on (o.question_id = q.id) " +
            "inner join capstone.feedback f on (q.feedback_id = f.id) " +
            "where a.user_id=:userId and f.id=:feedbackId", nativeQuery = true)
    int removeAllAnswerByUserAndFeedback(@Param("userId") int userId, @Param("feedbackId") int feedbackId);

}