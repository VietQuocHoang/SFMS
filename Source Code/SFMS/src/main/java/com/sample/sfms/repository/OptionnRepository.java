package com.sample.sfms.repository;

import com.sample.sfms.entity.Optionn;
import com.sample.sfms.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@org.springframework.stereotype.Repository
public interface OptionnRepository extends JpaRepository<Optionn, Integer> {
    @Query("select o from Optionn o where " +
            "o.questionByQuestionId.id = :questionID")
    List<Optionn> findByQuestionId(@Param("questionID")int questionID);

}
