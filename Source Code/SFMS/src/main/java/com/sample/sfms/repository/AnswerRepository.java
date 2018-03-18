package com.sample.sfms.repository;

import com.sample.sfms.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

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

}