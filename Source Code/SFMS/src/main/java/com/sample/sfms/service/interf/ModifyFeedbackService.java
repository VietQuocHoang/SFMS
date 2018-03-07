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

    public ResponseEntity<List<Feedback>> saveNewFeadbacks(int templateId, int[]targetIds);

    public ResponseEntity<Feedback> updateTemplate(ModifyFeedbackModel model);

    public ResponseEntity<Feedback> deleteFeedback(int id);

    public ResponseEntity<Feedback> addTarget(int typeId, int targetId);

    public ResponseEntity<UserFeedback> addConductor(int targetId, int userId);

    public ResponseEntity<UserFeedback> removeConductor(int targetId, int userId);

    public ResponseEntity<UserFeedback> addViewer(int targetId, int userId);

    public ResponseEntity<UserFeedback> removeViewer(int targetId, int userId);

    public ResponseEntity<Feedback> setStart(Date start, int feedbbackId);

    public ResponseEntity<Feedback> setEnd(Date end, int feedbackId);

    public ResponseEntity<List<User>> loadConductors(int id);//id of feedback contains target

    public ResponseEntity<List<User>> loadViewers(int id);//id of feedback contains target

    public ResponseEntity<List<Department>> loadDepartmentTargets(int[] targetIds);//ids of feedbacks contain targets

    public ResponseEntity<List<Major>> loadMajorTargets(int[] targetIds);//ids of feedbacks contain targets

    public ResponseEntity<List<Course>> loadCourseTargets(int[] targetIds);//ids of feedbacks contain targets

    public ResponseEntity<List<Clazz>> loadClazzTargets(int[] targetIds);//ids of feedbacks contain targets
}