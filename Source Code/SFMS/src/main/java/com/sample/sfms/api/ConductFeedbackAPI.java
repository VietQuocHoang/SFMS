package com.sample.sfms.api;

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
@RequestMapping("/api/conduct-feedback")
public class ConductFeedbackAPI {

    @Autowired
    private ConductFeedbackService conductFeedbackService;

    @Autowired
    private FeedbackService feedbackService;

    @GetMapping("/list")
    private List<UserFeedback> getFeedbacks(){
        List<UserFeedback> feedbacks = feedbackService.getFeedbacksByUserId();
        return feedbacks;
    }

    @PostMapping(value = "/save", consumes = "application/json", produces = "application/json")
    private ResponseEntity saveConductedFeedback(
            @RequestBody ConductAnswerWrapper conductAnswerWrapper) {
        return conductFeedbackService.saveAnswer(conductAnswerWrapper);
    }

    /*@GetMapping(value = "/list")
    private ModelAndView getListConductFeedback() {
        ModelAndView mav = new ModelAndView("view-list-conduct-feedback");
        List<UserFeedback> feedbacks = feedbackService.getFeedbacksByUserId();
        mav.addObject("feedbacks", feedbacks);
        return mav;
    }*/

    @GetMapping(value = "/{id}")
    private Feedback conductFeedback(@PathVariable("id") int feedbackId) {
        ModelAndView mav = new ModelAndView();
        Feedback feedback = feedbackService.findFeedbackToConduct(feedbackId);
        return feedback;
    }


}