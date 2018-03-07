package com.sample.sfms.service.impl;

import com.sample.sfms.entity.Feedback;
import com.sample.sfms.entity.Type;
import com.sample.sfms.model.feedback.FeedbackCreateModel;
import com.sample.sfms.repository.FeedbackRepository;
import com.sample.sfms.repository.TypeRepository;
import com.sample.sfms.service.interf.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Binh Nguyen on 21-Feb-18.
 */
@Service("feedbackService")
public class FeedbackServiceImpl implements FeedbackService {

    static Logger logger = Logger.getLogger(FeedbackServiceImpl.class.getName());
    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private TypeRepository typeRepository;

    @Override
    public List<Feedback> getListTemplate(boolean isTemplate) {
        return feedbackRepository.findByIsTemplate(isTemplate);
    }

    @Override
    public Feedback findFeedbackById(int id) {
        return feedbackRepository.findById(id);
    }

    @Override
    public int save(FeedbackCreateModel model) throws Exception {
        Feedback feedback = new Feedback();
        Type type = typeRepository.getOne(1); //Hard code. Please fix when release
        feedback.setTypeByTypeId(type);
        feedback.setIsTemplate(false); //Hard code

        feedbackRepository.save(feedback);
        return feedback.getId();
    }

    @Override
    public List<Feedback> loadListFeedback(String type) {
        List<Feedback> list = new ArrayList<>();
        switch (type) {
            case "Major":
                list.addAll(feedbackRepository.getListMajorFeedback());
                break;
            case "Course":
                list.addAll(feedbackRepository.getListCourseFeedback());
                break;
            case "Dep":
                list.addAll(feedbackRepository.getListDepFeedback());
                break;
            case "Class":
                list.addAll(feedbackRepository.getListClassFeedback());
                break;
            default: break;
        }
        return list;
    }
}
