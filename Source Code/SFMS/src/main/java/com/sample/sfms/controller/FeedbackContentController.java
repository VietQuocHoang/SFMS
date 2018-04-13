package com.sample.sfms.controller;

import com.sample.sfms.entity.Feedback;
import com.sample.sfms.entity.Question;
import com.sample.sfms.service.interf.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Collection;

/**
 * Created by Binh Nguyen on 10-Mar-18.
 */
@RestController
public class FeedbackContentController {

    @Autowired
    private FeedbackService feedbackService;

    @RequestMapping(value = "/edit-feedback-content/{id}")
    private ModelAndView editFeedbackModel(@PathVariable("id") int feedbackId){
        ModelAndView mv = new ModelAndView("edit-feedback-content");
        Feedback feedback = feedbackService.findFeedbackById(feedbackId);
        mv.addObject("feedback", feedback);
        return mv;
    }

    @RequestMapping(value = "/modify-suggested-improvement")
    private ModelAndView editFeedbackSuggestion (HttpSession session){
        int feedbackId = (int)session.getAttribute("id");
        Feedback feedback = feedbackService.findFeedbackById(feedbackId);
        Collection<Question> questionList = feedback.getQuestionsById();
        if (questionList.size() <= 0) {
            ModelAndView mv = new ModelAndView("edit-feedback-suggested-improvement");
            mv.addObject("feedback", null);
            mv.addObject("template", null);
            return mv;
        } else {
            boolean flag = false;
            for (Question question : questionList) {
                if (question.getSuggestion() != null) {
                    flag = true;
                    break;
                }
            }
            System.out.println("Flag: " + flag);

            if (flag) {
                //Feedback da tung co suggestion nao
                ModelAndView mv = new ModelAndView("edit-feedback-suggested-improvement");
                mv.addObject("feedback", feedback);
                mv.addObject("template", null);
                return mv;
            } else {
                //Feedback chua tung co suggestion nao
                if (feedback.getFeedbackByReferenceId() != null) {
                    //Feedback co template
                    System.out.println("feedback.getFeedbackByReferenceId(): " + feedback.getFeedbackByReferenceId().getId());
                    ModelAndView mv = new ModelAndView("edit-feedback-suggested-improvement");
                    mv.addObject("template", feedback.getFeedbackByReferenceId());
                    mv.addObject("feedback", feedback);
                    return mv;
                } else {
                    ModelAndView mv = new ModelAndView("edit-feedback-suggested-improvement");
                    mv.addObject("feedback", feedback);
                    mv.addObject("template", null);
                    //System.out.println("feedback.getFeedbackByReferenceId(): " + feedback.getFeedbackByReferenceId());
                    //ModelAndView mv = new ModelAndView("create-feedback-suggested-improvement");
                    return mv;
                }
            }
        }
    }

    @GetMapping(value = "/preview-feedback/{id}")
    private ModelAndView previewFeedback(@PathVariable("id") int feedbackId) {
        ModelAndView mav = new ModelAndView();
        Feedback feedback = feedbackService.findFeedbackToPreview(feedbackId);
        if (feedback == null) {
            mav.setViewName("forbidden");
        } else {
            mav.setViewName("preview-content");
            mav.addObject("feedback", feedback);
        }
        return mav;
    }
}
