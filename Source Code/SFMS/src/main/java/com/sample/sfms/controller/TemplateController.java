package com.sample.sfms.controller;

import com.sample.sfms.entity.Feedback;
import com.sample.sfms.service.interf.FeedbackService;
import com.sample.sfms.service.interf.ModifyFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Binh Nguyen on 14-Mar-18.
 */
@RestController
@RequestMapping("/template")
public class TemplateController {

    @Autowired
    private FeedbackService feedbackService;

    @Autowired
    ModifyFeedbackService modifyService;

    @RequestMapping(value = "/select-template")
    private ModelAndView loadListTemplate(){
        ModelAndView mv = new ModelAndView("select-template");
        List<Feedback> templates = feedbackService.getListTemplate(true);
        System.out.print("Template size: " + templates.size());
        mv.addObject("templates", templates);
        return mv;
    }

    @GetMapping(value = "/create/{id}")
    private ModelAndView createFeedbackByGet(@PathVariable("id") int templateId, HttpSession session) {
        ModelAndView mv = new ModelAndView("create-from-template-content");
        Feedback response = modifyService.createFeedbackFromTemplate(templateId).getBody(); //HARD CODE TO DO
        session.setAttribute("id", response.getId());
        Feedback template = feedbackService.findFeedbackById(templateId); //HARD CODE TO DO
      //  if (template.getQuestionsById().size() == 0) {
      //      mv.addObject("template", null);
       // } else {
            mv.addObject("template", template);
       // }
        mv.addObject("MFModel", response);
        return mv;
    }

    @PostMapping(value = "/create")
    private ModelAndView createFeedbackByPost(@RequestParam("templateId") String templateId, HttpSession session) {
        int templateIdInt = Integer.parseInt(templateId);
        ModelAndView mv = new ModelAndView("create-from-template-content");
        Feedback response = modifyService.createFeedbackFromTemplate(templateIdInt).getBody(); //HARD CODE TO DO
        session.setAttribute("id", response.getId());
        Feedback template = feedbackService.findFeedbackById(templateIdInt); //HARD CODE TO DO
        mv.addObject("MFModel", response);
        mv.addObject("template", template);
        return mv;
    }
}
