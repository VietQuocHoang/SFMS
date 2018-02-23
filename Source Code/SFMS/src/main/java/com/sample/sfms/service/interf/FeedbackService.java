package com.sample.sfms.service.interf;

import com.sample.sfms.entity.Feedback;

import java.util.List;

public interface FeedbackService {

    List<Feedback> getListTemplate(boolean isTemplate);

    Feedback findFeedbackById (int id);

}

