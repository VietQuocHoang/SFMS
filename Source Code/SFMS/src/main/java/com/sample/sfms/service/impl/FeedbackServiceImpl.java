package com.sample.sfms.service.impl;

import com.sample.sfms.entity.Feedback;
import com.sample.sfms.repository.FeedbackRepository;
import com.sample.sfms.service.interf.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Binh Nguyen on 21-Feb-18.
 */
@Service("feedbackService")
public class FeedbackServiceImpl implements FeedbackService {

    static Logger logger = Logger.getLogger(RoleServiceImpl.class.getName());
    @Autowired
    private FeedbackRepository feedbackRepository;

    @Override
    public List<Feedback> getListTemplate(boolean isTemplate) {
        return feedbackRepository.findByIsTemplate(isTemplate);
    }

    @Override
    public Feedback findFeedbackById(int id) {
        return feedbackRepository.findById(id);
    }
}
