package com.sample.sfms.api;

import com.sample.sfms.entity.Feedback;
import com.sample.sfms.service.interf.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Binh Nguyen on 21-Feb-18.
 */

@RestController
@RequestMapping("/api/feedbacks")
public class FeedbackAPI {

    //test new branch
    @Autowired
    private FeedbackService feedbackService;

    @GetMapping
    private List<Feedback> getListTemplate(){
        return feedbackService.getListTemplate(true);
    }

    @GetMapping("/{id}")
    private Feedback getTemplateContent(@PathVariable("id") int id){
        return feedbackService.findFeedbackById(id);
    }
}
