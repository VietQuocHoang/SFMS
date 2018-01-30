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
    private ModelAndView init() {
        return new ModelAndView("login");
    }

    @GetMapping(value = "/roles")
    private String role() {
        return "role";
    }

    @GetMapping(value = "/users")
    private String user() {
        return "user";
    }

    @GetMapping(value = "/conduct-feedback")
    private String conductFeedback() {
        return "conduct";
    }

    @GetMapping(value = "/feedbacks")
    private String feedback() {
        return "feedback";
    }

    @GetMapping(value = "/home")
    private ModelAndView home() {
        return new ModelAndView("home");
    }

}
