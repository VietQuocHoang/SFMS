package com.sample.sfms.repository;

import com.sample.sfms.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@org.springframework.stereotype.Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {
   List<Feedback> findByIsTemplate(boolean isTemplate);
}