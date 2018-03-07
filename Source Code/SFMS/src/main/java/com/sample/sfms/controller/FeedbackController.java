package com.sample.sfms.controller;

import com.sample.sfms.entity.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.sample.sfms.service.interf.FeedbackService;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class FeedbackController {
    @Autowired
    FeedbackService feedbackService;

    @RequestMapping(value = "/view-list-feedback/list/{selectedValue}")
    private ModelAndView listFeedbackModel(@PathVariable(value = "selectedValue") String selectedValue){
        ModelAndView mv = new ModelAndView("view-list-feedback");
        String type = String.valueOf(selectedValue);
        List<Feedback> feedbacks = feedbackService.loadListFeedback(type);
        mv.addObject("feedbacks", feedbacks);
        mv.addObject("type", type);
        return mv;
    }
}