package com.sample.sfms.controller;


import com.sample.sfms.service.interf.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class TestController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/preview-content")
    private ModelAndView previewFeedback() {
        return new ModelAndView("preview-content");
    }

    @GetMapping(value = "/overview-feedback")
    private ModelAndView overviewFeedback() {
        return new ModelAndView("overview-feedback");
    }

    @GetMapping(value = "/modify-feedback-target")
    private ModelAndView ModifyFeedbackTarget() {
        return new ModelAndView("modify-feedback-target");
    }

    @GetMapping(value = "/modify-feedback-conductors")
    private ModelAndView ModifyFeedbackConductor() {
        return new ModelAndView("modify-feedback-conductors");
    }

    @GetMapping(value = "/modify-feedback-viewers")
    private ModelAndView ModifyFeedbackViewers() {
        return new ModelAndView("modify-feedback-viewers");
    }

    @GetMapping(value = "/create-feedback-suggested-improvement")
    private ModelAndView ModifyFeedbackSuggestedImprovement() {
        return new ModelAndView("create-feedback-suggested-improvement");
    }

    @GetMapping(value = "/home")
    private ModelAndView home() {
        return new ModelAndView("dashboard");
    }

    @GetMapping(value = "/nav")
    private ModelAndView nav() {
        return new ModelAndView("fragments/navbar-side");
    }

    @GetMapping(value = "/select-template")
    private ModelAndView selectFeedback(){
        return new ModelAndView("select-template");
    }

    @GetMapping(value = "/create-feedback-content")
    private ModelAndView createFeedbackContent(){
        return new ModelAndView("create-feedback-content");
    }

    @GetMapping(value = "/edit-feedback-content")
    private ModelAndView editFeedbackContent(){
        return new ModelAndView("edit-feedback-content");
    }

    @GetMapping(value = "/my-report")
    private ModelAndView myReport() {
        return new ModelAndView("view-my-report");
    }

    @GetMapping(value = "/view-report-details")
    private ModelAndView viewReportDetails(){
        return new ModelAndView("view-report-details");
    }

    @GetMapping(value = "/modify-user-details")
    private ModelAndView modifySelectedUser(){
        return new ModelAndView("modify-user-details");
    }
}
