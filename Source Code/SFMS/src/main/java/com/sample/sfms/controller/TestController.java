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


    @GetMapping(value = {"/", "/login"})
    private ModelAndView login() {
        return new ModelAndView("login");
    }

    @GetMapping(value = "/conduct-feedback")
    private ModelAndView conductFeedback() {
        return new ModelAndView("conduct-feedback");
    }

    @GetMapping(value = "/overview-feedback")
    private ModelAndView overviewFeedback() {
        return new ModelAndView("overview-feedback");
    }

    @GetMapping(value = "/skeleton")
    private ModelAndView skeleton() {
        return new ModelAndView("skeleton");
    }

    @GetMapping(value = "/nav")
    private ModelAndView nav() {
        return new ModelAndView("fragments/navbar-side");
    }
}
