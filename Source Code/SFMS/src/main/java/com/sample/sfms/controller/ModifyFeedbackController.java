package com.sample.sfms.controller;

import com.sample.sfms.entity.Feedback;
import com.sample.sfms.model.ModifyFeedbackModel;
import com.sample.sfms.service.interf.ModifyFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * Created by MyPC on 25/02/2018.
 */
@RestController
public class ModifyFeedbackController {

    @Autowired
    ModifyFeedbackService modifyService;

    @PostMapping(value = "/modify-feedback/create")
    private ModelAndView createFeedbackModel(@RequestParam("title") String title, @RequestParam("description") String description, HttpSession session){
        ModelAndView mv = new ModelAndView("create-feedback-content");
        Feedback response = modifyService.createEmptyFeedback(title, description).getBody();
        session.setAttribute("id", response.getId());
        mv.addObject("MFModel", response);
        return mv;
    }

    @PostMapping(value = "/modify-feedback/create/{id}")
    private ModelAndView createFeedbackModel(@PathVariable("templateId") int templateId, HttpSession session){
        ModelAndView mv = new ModelAndView("create-feedback-content");
        Feedback response = modifyService.createFeedbackFromTemplate(templateId).getBody();
        session.setAttribute("id", response.getId());
        mv.addObject("MFModel", response);
        return mv;
    }
}
