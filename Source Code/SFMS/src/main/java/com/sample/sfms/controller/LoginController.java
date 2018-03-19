package com.sample.sfms.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.sample.sfms.entity.User;
import com.sample.sfms.service.interf.UserService;
import com.sample.sfms.view.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/access-denied")
    private ModelAndView accessDenied() {
        return new ModelAndView("forbidden");
    }


    @JsonView(UserView.authenticatedUser.class)
    @GetMapping("/current")
    private User getCurrentAuthenticatedUser() {
        return userService.getCurrentAuthenticatedUser();
    }

}
