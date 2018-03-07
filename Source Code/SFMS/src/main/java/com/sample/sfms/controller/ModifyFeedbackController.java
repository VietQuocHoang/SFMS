package com.sample.sfms.controller;

import com.sample.sfms.api.responseModel.Response;
import com.sample.sfms.entity.Feedback;
import com.sample.sfms.model.ModifyFeedbackModel;
import com.sample.sfms.model.feedback.FeedbackCreateModel;
import com.sample.sfms.model.question.AddQuestionModel;
import com.sample.sfms.service.interf.ModifyFeedbackService;
import com.sample.sfms.service.interf.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

/**
 * Created by MyPC on 25/02/2018.
 */
@RestController
public class ModifyFeedbackController {

    @Autowired
    ModifyFeedbackService modifyService;

    @Autowired
    QuestionService questionService;

    @PostMapping(value = "/modify-feedback/create")
    private ModelAndView createFeedbackModel(@RequestParam("title") String title, @RequestParam("description") String description, HttpSession session){
        ModelAndView mv = new ModelAndView("create-feedback-content");
        Feedback response = modifyService.createEmptyFeedback(title, description).getBody();
        session.setAttribute("id", response.getId());
        mv.addObject("MFModel", response);
        return mv;
        //test
    }

    @PostMapping(value = "/modify-feedback/create/{id}")
    private ModelAndView createFeedbackModel(@PathVariable("templateId") int templateId, HttpSession session){
        ModelAndView mv = new ModelAndView("create-feedback-content");
        Feedback response = modifyService.createFeedbackFromTemplate(templateId).getBody();
        session.setAttribute("id", response.getId());
        mv.addObject("MFModel", response);
        return mv;
    }

  /*  @PostMapping(value = "/save-question")
    private ModelAndView saveQuestion(@RequestBody FeedbackCreateModel model) {
        if(model.valid()) {
            try {
                int feedbackId = model.getId();

                for (AddQuestionModel question :
                        model.getQuestions()) {
                    question.setFeedbackId(feedbackId);
                    questionService.addQuestion(question);
                }

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }

        }
        ModelAndView mv = new ModelAndView("redirect:/overview-feedback");
        return mv;
    }*/
}
