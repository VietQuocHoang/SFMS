package com.sample.sfms.controller;

import com.sample.sfms.api.responseModel.Response;
import com.sample.sfms.entity.Feedback;
import com.sample.sfms.model.ModifyFeedbackModel;
import com.sample.sfms.model.feedback.FeedbackCreateModel;
import com.sample.sfms.model.question.AddQuestionModel;
import com.sample.sfms.service.interf.FeedbackService;
import com.sample.sfms.service.interf.ModifyFeedbackService;
import com.sample.sfms.service.interf.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MyPC on 25/02/2018.
 */
@RestController
@RequestMapping("/modify-feedback")
class ModifyFeedbackController {

    @Autowired
    ModifyFeedbackService modifyService;

    @Autowired
    QuestionService questionService;

    @PostMapping(value = "/create")
    private ModelAndView createFeedback(@RequestParam("title") String title, @RequestParam("description") String description, HttpSession session) {
        ModelAndView mv = new ModelAndView("create-feedback-content");
        Feedback response = modifyService.createEmptyFeedback(title, description).getBody();
        session.setAttribute("id", response.getId());
        mv.addObject("MFModel", response);
        return mv;
    }

    @PostMapping(value = "/create/{id}")
    private ModelAndView createFeedback(@PathVariable("id") int templateId, HttpSession session) {
        ModelAndView mv = new ModelAndView("create-feedback-content");
        Feedback response = modifyService.createFeedbackFromTemplate(templateId).getBody();
        session.setAttribute("id", response.getId());
        mv.addObject("MFModel", response);
        return mv;
    }

    @GetMapping(value = "/overview")
    private ModelAndView getFeedbackOverview(HttpSession session) {
        ModelAndView mv = new ModelAndView("overview-feedback");
        Feedback feedback = modifyService.getFeedback((int)session.getAttribute("id")).getBody();
        if (session.getAttribute("targetIds") == ""||session.getAttribute("targetIds") == null)
            session.setAttribute("targetIds", new ArrayList<Integer>());
        mv.addObject("feedback", feedback);
        mv.addObject("startdate", feedback.getStartDate() == null ? "" : new SimpleDateFormat("yyyy-MM-dd").format(feedback.getStartDate()));
        mv.addObject("enddate", feedback.getEndDate() == null ? "" : new SimpleDateFormat("yyyy-MM-dd").format(feedback.getEndDate()));
        mv.addObject("alltypes", modifyService.loadAllTypes().getBody());
        mv.addObject("allSemesters", modifyService.loadAllSemesters().getBody());
        return mv;
    }

    @GetMapping(value = "/overview/{id}")
    private ModelAndView getFeedbackOverview(@PathVariable("id") int id, HttpSession session) {
        ModelAndView mv = new ModelAndView("overview-feedback");
        Feedback feedback = modifyService.getFeedback(id).getBody();
        session.setAttribute("id", feedback.getId());
        if (session.getAttribute("targetIds") == ""||session.getAttribute("targetIds") == null) {
            List<Integer> targetIds = new ArrayList<>();
            if (feedback.hasTarget())
                targetIds.add(feedback.getId());
            session.setAttribute("targetIds", targetIds);
        }
        mv.addObject("feedback", feedback);
        mv.addObject("startdate", feedback.getStartDate() == null ? "" : new SimpleDateFormat("yyyy-MM-dd").format(feedback.getStartDate()));
        mv.addObject("enddate", feedback.getEndDate() == null ? "" : new SimpleDateFormat("yyyy-MM-dd").format(feedback.getEndDate()));
        mv.addObject("alltypes", modifyService.loadAllTypes().getBody());
        mv.addObject("allSemesters", modifyService.loadAllSemesters().getBody());
//        mv.addObject("targets", modifyService.loadTargets((int[])session.getAttribute("targetIds")));
        return mv;
    }

    @GetMapping(value = "/target")
    private ModelAndView getListAvailableTarget(HttpSession session) {
        ModelAndView mv = new ModelAndView("modify-feedback-target");
        Feedback feedback = modifyService.getFeedback((int)session.getAttribute("id")).getBody();
        mv.addObject("feedback", feedback);
        return mv;
    }

    @GetMapping(value = "/conductor/{id}")
    private ModelAndView getListConductor(@PathVariable("id") int id, HttpSession session) {
        ModelAndView mv = new ModelAndView("modify-feedback-conductors");
        mv.addObject("targetId", id);
        return mv;
    }
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
