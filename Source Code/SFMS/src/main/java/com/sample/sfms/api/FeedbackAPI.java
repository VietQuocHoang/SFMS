package com.sample.sfms.api;

import com.sample.sfms.api.responseModel.Response;
import com.sample.sfms.entity.Answer;
import com.sample.sfms.entity.Feedback;
import com.sample.sfms.model.feedback.FeedbackCreateModel;
import com.sample.sfms.model.question.AddQuestionModel;
import com.sample.sfms.service.interf.FeedbackService;
import com.sample.sfms.service.interf.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
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

    @Autowired
    private QuestionService questionService;

    @GetMapping
    private List<Feedback> getListTemplate(){
        return feedbackService.getListTemplate(true);
    }

    @GetMapping("/{id}")
    private Feedback getTemplateContent(@PathVariable("id") int id){
        return feedbackService.findFeedbackById(id);
    }

    @RequestMapping(value = "/save-question", method = RequestMethod.POST)
    @Transactional
    public Response saveQuestion(@RequestBody FeedbackCreateModel model) {
        if(model.valid()) {
            try {
                //int feedbackId = feedbackService.save(model);
                int feedbackId = model.getId();

                for (AddQuestionModel question :
                        model.getQuestions()) {
                    question.setFeedbackId(feedbackId);
                    questionService.addQuestion(question);
                }
                return new Response(true, "overview-feedback");
            } catch (Exception ex) {
                return new Response(false, ex.getMessage());
            }

        }
        return new Response(false, "Xin kiểm tra lại feedback đã hợp lệ chưa");
    }
}
