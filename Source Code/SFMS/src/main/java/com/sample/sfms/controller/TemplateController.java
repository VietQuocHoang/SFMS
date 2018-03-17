package com.sample.sfms.controller;

import com.sample.sfms.entity.Feedback;
import com.sample.sfms.service.interf.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by Binh Nguyen on 14-Mar-18.
 */
@RestController
@RequestMapping("/template")
public class TemplateController {

    @Autowired
    private FeedbackService feedbackService;

    @RequestMapping(value = "/select-template")
    private ModelAndView loadListTemplate(){
        ModelAndView mv = new ModelAndView("select-template");
        List<Feedback> templates = feedbackService.getListTemplate(true);
        System.out.print("Template size: " + templates.size());
        mv.addObject("templates", templates);
        return mv;
    }
}
