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

    public ResponseEntity<Feedback> createEmptyFeedback();

    public ResponseEntity<Feedback> createFeedbackFromTemplate(int id);

    public ResponseEntity<List<Feedback>> saveNewFeadbacks(ModifyFeedbackModel model);

    public ResponseEntity<Feedback> updateTemplate(ModifyFeedbackModel model);

    public ResponseEntity<Feedback> deleteFeedback(int id);

    public ResponseEntity<Feedback> addTarget(int typeId, int targetId);

    public FeedbackDetailsModel customizeConductors(FeedbackDetailsModel model, int[] conductorIds);

    public FeedbackDetailsModel customizeViewers(FeedbackDetailsModel model, int[] viewerIds);

    public ResponseEntity<ModifyFeedbackModel> setStart(Date start, Feedback feedback);

    public ResponseEntity<ModifyFeedbackModel> setEnd(Date end, Feedback feedback);

    public ResponseEntity<List<User>> loadConductors(int id);//id of feedback contains target

    public ResponseEntity<List<User>> loadViewers(int id);//id of feedback contains target

    public ResponseEntity<List<User>> loadTargets(int[] id);//ids of feedbacks contain targets
}