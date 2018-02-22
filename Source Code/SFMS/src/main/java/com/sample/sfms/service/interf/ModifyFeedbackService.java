package com.sample.sfms.service.interf;

import com.sample.sfms.entity.Clazz;
import com.sample.sfms.entity.Course;
import com.sample.sfms.entity.Feedback;
import com.sample.sfms.entity.Major;
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

    public Feedback getFeedback(int id);

    public List<ResponseEntity<Feedback>> saveFeadbacks(ModifyFeedbackModel modifyFeedbackModel);

    public ResponseEntity<ModifyFeedbackModel> addTarget(int typeId, int targetId);

    public ResponseEntity<ModifyFeedbackModel> removeTarget(int typeId, int targetId);

    public ResponseEntity<ModifyFeedbackModel> autoGenerateConductors(int targetId);

    public ResponseEntity<ModifyFeedbackModel> autoGenerateViewers(int targetId);

    public ResponseEntity<ModifyFeedbackModel> customizeConductors(int targetId, int[] conductorIds);

    public ResponseEntity<ModifyFeedbackModel> customizeViewers(int targetId, int[] viewerIds);

    public ResponseEntity<ModifyFeedbackModel> setStart(Date start);

    public ResponseEntity<ModifyFeedbackModel> setEnd(Date end);
}