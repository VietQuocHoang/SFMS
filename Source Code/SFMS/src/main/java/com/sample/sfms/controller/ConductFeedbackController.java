package com.sample.sfms.controller;

import com.sample.sfms.entity.Feedback;
import com.sample.sfms.entity.UserFeedback;
import com.sample.sfms.model.answer.ConductAnswerWrapper;
import com.sample.sfms.service.interf.ConductFeedbackService;
import com.sample.sfms.service.interf.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/conduct-feedback")
public class ConductFeedbackController {

    @Autowired
    private ConductFeedbackService conductFeedbackService;

    @Autowired
    private FeedbackService feedbackService;

    @GetMapping(value = "/list")
    private ModelAndView getListConductFeedback() {
        ModelAndView mav = new ModelAndView("view-list-conduct-feedback");
        List<UserFeedback> feedbacks = feedbackService.getFeedbacksByUserId();
        mav.addObject("feedbacks", feedbacks);
        return mav;
    }

    @GetMapping(value = "/{id}")
    private ModelAndView conductFeedback(@PathVariable("id") int feedbackId) {
        ModelAndView mav = new ModelAndView();
        Feedback feedback = feedbackService.findFeedbackToConduct(feedbackId);
        if (feedback == null) {
            mav.setViewName("forbidden");
        } else {
            mav.setViewName("conduct-feedback");
            mav.addObject("feedback", feedback);
        }
        return mav;
    }

    @PostMapping(value = "/save", consumes = "application/json", produces = "application/json")
    private ResponseEntity saveConductedFeedback(@RequestBody ConductAnswerWrapper conductAnswerWrapper) {
        return conductFeedbackService.saveAnswer(conductAnswerWrapper);
    }

}
