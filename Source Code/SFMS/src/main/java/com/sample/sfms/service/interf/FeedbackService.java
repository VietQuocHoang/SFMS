package com.sample.sfms.service.interf;

import com.sample.sfms.entity.Feedback;
import com.sample.sfms.entity.UserFeedback;
import com.sample.sfms.model.feedback.FeedbackCreateModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FeedbackService {

    List<Feedback> getListTemplate(boolean isTemplate);

    Feedback findFeedbackById (int id);

    int save(FeedbackCreateModel model) throws Exception;

    List<Feedback> loadListFeedback (String type);

    ResponseEntity getNotConductedFeedbacksByUserId();

    ResponseEntity getNotConductedFeedbacksByUserIdMobile(String username);

    ResponseEntity getNotificationData();

    List<UserFeedback> getFeedbacksByUserId();

    Feedback findFeedbackToConduct(int id);

    Feedback findFeedbackToConductMobile(String username, int id);

    Feedback findFeedbackToPreview(int feedbackId);

    List<Feedback> findAllFeedbackNotTemplate();

    int deactiveTemplate (int templateId);

    ResponseEntity switchPublish(int feedbackId);
}

