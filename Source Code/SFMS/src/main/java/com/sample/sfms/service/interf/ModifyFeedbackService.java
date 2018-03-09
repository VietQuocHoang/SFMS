package com.sample.sfms.service.interf;

import com.sample.sfms.entity.*;
import com.sample.sfms.model.FeedbackDetailsModel;
import com.sample.sfms.model.ModifyFeedbackModel;
import org.springframework.http.ResponseEntity;

import java.security.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by MyPC on 23/02/2018.
 */
public interface ModifyFeedbackService {

    public ResponseEntity<Feedback> getFeedback(int id);

    public ResponseEntity<Feedback> createEmptyFeedback(String title, String description);

    public ResponseEntity<Feedback> createFeedbackFromTemplate(int id);

    public ResponseEntity<List<Feedback>> savePublishFeadbacks(int feedbackId, List<Integer>targetIds);

    public ResponseEntity<List<Feedback>> saveTemplateFeadbacks(int feedbackId, List<Integer>targetIds);

    public ResponseEntity<Feedback> updateSelectedTemplate(int feedbackId, List<Integer> targetIds);

    public ResponseEntity<Feedback> deleteFeedback(int id);

    public ResponseEntity<Feedback> addTarget(int feedbackId, int targetId, List<Integer> targetIds);

    public ResponseEntity<Feedback> removeTarget(int id, List<Integer> targetIds);

    public ResponseEntity<List<Feedback>> deleteFeedbacks(List<Integer> targetIds);

    public ResponseEntity<UserFeedback> addConductor(int targetId, int userId);

    public ResponseEntity<UserFeedback> removeConductor(int targetId, int userId);

    public ResponseEntity<UserFeedback> addViewer(int targetId, int userId);

    public ResponseEntity<UserFeedback> removeViewer(int targetId, int userId);

    public ResponseEntity<Feedback> saveFeedback(Feedback feedback);

    public ResponseEntity<Feedback> updateSemester(int semesterId, int feedbackId);

    public ResponseEntity<Feedback> updateType(int typeId, int feedbackId);

    public ResponseEntity<Feedback> setStart(Date start, int feedbbackId);

    public ResponseEntity<Feedback> setEnd(Date end, int feedbackId);

    public ResponseEntity<List<User>> loadConductors(int id);//id of feedback contains target

    public ResponseEntity<List<User>> loadViewers(int id);//id of feedback contains target

    public ResponseEntity<List<Department>> loadDepartmentTargets(List<Integer> targetIds);//ids of feedbacks contain targets

    public ResponseEntity<List<Major>> loadMajorTargets(List<Integer> targetIds);//ids of feedbacks contain targets

    public ResponseEntity<List<Course>> loadCourseTargets(List<Integer> targetIds);//ids of feedbacks contain targets

    public ResponseEntity<List<Clazz>> loadClazzTargets(List<Integer> targetIds);//ids of feedbacks contain targets

    public ResponseEntity<List<Feedback>> loadTargets(List<Integer> targetIds);//ids of feedbacks contain targets
}