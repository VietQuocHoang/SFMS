package com.sample.sfms.controller;

import com.sample.sfms.model.AddQuestionModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class QuestionController {

    @GetMapping(value = "/add")
    public ModelAndView add(AddQuestionModel model) {

        return new ModelAndView("overview-feedback");
    }

}
