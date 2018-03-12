package com.sample.sfms.controller;

import com.sample.sfms.entity.Feedback;
import com.sample.sfms.service.interf.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * Created by Binh Nguyen on 10-Mar-18.
 */
@RestController
public class FeedbackContentController {

    @Autowired
    private FeedbackService feedbackService;

    @RequestMapping(value = "/edit-feedback-content/{id}")
    private ModelAndView editFeedbackModel(@PathVariable("id") int feedbackId){
        ModelAndView mv = new ModelAndView("edit-feedback-content");
        Feedback feedback = feedbackService.findFeedbackById(feedbackId);
        mv.addObject("feedback", feedback);
        return mv;
    }
}
