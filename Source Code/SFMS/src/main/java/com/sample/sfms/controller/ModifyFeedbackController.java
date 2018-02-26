package com.sample.sfms.controller;

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

    @GetMapping(value = "modify-feedback/create/{id}")
    private ModelAndView createFeedbackModel(@PathVariable("templateId") int templateId){
        ModelAndView mv = new ModelAndView("create-feedback-content");
        ModifyFeedbackModel response;
        if(templateId==0) response = modifyService.createEmptyFeedback();
        else response = modifyService.createFeedbackFromTemplate(templateId);
        mv.addObject("MFModel", response);
        return mv;
    }
}
