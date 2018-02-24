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

    public ResponseEntity<ModifyFeedbackModel> createEmptyFeedback();

    public ResponseEntity<ModifyFeedbackModel> createFeedbackFromTemplate(int id);

    public ResponseEntity<List<Feedback>> saveFeadbacks(ModifyFeedbackModel model);

    public ResponseEntity<ModifyFeedbackModel> addTarget(int typeId, int targetId, ModifyFeedbackModel model);

    public ResponseEntity<ModifyFeedbackModel> removeTarget(int typeId, int targetId, ModifyFeedbackModel model);

    public List<User> autoGenerateConductors(FeedbackDetailsModel model);

    public List<User> autoGenerateViewers(FeedbackDetailsModel model);

    public FeedbackDetailsModel customizeConductors(FeedbackDetailsModel model, int[] conductorIds);

    public FeedbackDetailsModel customizeViewers(FeedbackDetailsModel model, int[] viewerIds);

    public ResponseEntity<ModifyFeedbackModel> setStart(Date start, Feedback feedback);

    public ResponseEntity<ModifyFeedbackModel> setEnd(Date end, Feedback feedback);
}