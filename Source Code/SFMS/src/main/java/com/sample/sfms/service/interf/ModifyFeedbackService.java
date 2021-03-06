package com.sample.sfms.service.interf;

import com.sample.sfms.entity.*;
import com.sample.sfms.model.FeedbackDetailsModel;
import com.sample.sfms.model.ModifyFeedbackModel;
import org.springframework.http.ResponseEntity;

import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by MyPC on 23/02/2018.
 */
public interface ModifyFeedbackService {

    public ResponseEntity<Feedback> getFeedback(int id);

    public ResponseEntity<Feedback> createEmptyFeedback(String title, String description);

    public ResponseEntity<Feedback> createFeedbackFromTemplate(int id);

    public ResponseEntity savePublishFeadbacks(int feedbackId, List<Integer>targetIds);

    public ResponseEntity saveTemplateFeadback(int feedbackId, List<Integer>targetIds);

    public ResponseEntity updateSelectedTemplate(int feedbackId, List<Integer> targetIds);

    public ResponseEntity deleteFeedback(int id);

    public ResponseEntity cancelProcess(int id, List<Integer> targetIds);

    public ResponseEntity<List<Integer>> addTarget(int feedbackId, int targetId, List<Integer> targetIds);

    public ResponseEntity<List<Integer>> removeTarget(int id, List<Integer> targetIds);

    public ResponseEntity<List<Feedback>> deleteFeedbacks(List<Integer> targetIds);

    public ResponseEntity addConductor(int targetId, int userId, List<Integer> targetIds);

    public ResponseEntity removeConductor(int targetId, int userId, List<Integer> targetIds);

    public ResponseEntity<UserFeedback> addViewer(int targetId, int userId);

    public ResponseEntity<UserFeedback> removeViewer(int targetId, int userId);

    public ResponseEntity saveFeedback(Feedback feedback);

    public ResponseEntity updateSemester(int semesterId, int feedbackId);

    public ResponseEntity updateType(int typeId, int feedbackId);

    public ResponseEntity setStart(Date start, int feedbbackId);

    public ResponseEntity setEnd(Date end, int feedbackId);

    public ResponseEntity loadConductors(int id, List<Integer> targetIds);//id of feedback contains target

    public ResponseEntity loadViewers(int id);//id of feedback contains target

    public ResponseEntity loadDepartmentTargets(List<Integer> targetIds);//ids of feedbacks contain targets

    public ResponseEntity loadMajorTargets(List<Integer> targetIds);//ids of feedbacks contain targets

    public ResponseEntity loadCourseTargets(List<Integer> targetIds);//ids of feedbacks contain targets

    public ResponseEntity loadClazzTargets(List<Integer> targetIds);//ids of feedbacks contain targets

    public ResponseEntity loadTargets(List<Integer> targetIds);//ids of feedbacks contain targets

    public ResponseEntity loadAllTypes();

    public ResponseEntity loadAllSemesters();

    public ResponseEntity<List<Major>> loadAllMajors();

    public ResponseEntity<List<Course>> loadAllCourses();

    public ResponseEntity<List<Department>> loadAllDepartments();

    public ResponseEntity<List<Clazz>> loadAllClazz();

    public List filterSemester(String title);

    List<User> getAllStudents();

    List<User> getAllStaffs();

    List<User> getAllLecturers();

}