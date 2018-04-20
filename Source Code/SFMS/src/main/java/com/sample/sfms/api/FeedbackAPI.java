package com.sample.sfms.api;

import com.fasterxml.jackson.annotation.JsonView;
import com.sample.sfms.api.responseModel.Response;
import com.sample.sfms.entity.Feedback;
import com.sample.sfms.entity.Question;
import com.sample.sfms.model.FeedbackModifySuggestionModel;
import com.sample.sfms.model.ModifySuggestionModel;
import com.sample.sfms.model.answer.ConductAnswerWrapper;
import com.sample.sfms.model.feedback.FeedbackCreateModel;
import com.sample.sfms.model.feedback.FeedbackUpdateModel;
import com.sample.sfms.model.option.OptionCreateModel;
import com.sample.sfms.model.option.OptionUpdateModel;
import com.sample.sfms.model.question.AddQuestionModel;
import com.sample.sfms.model.question.UpdateQuestionModel;
import com.sample.sfms.service.interf.ConductFeedbackService;
import com.sample.sfms.service.interf.FeedbackService;
import com.sample.sfms.service.interf.QuestionService;
import com.sample.sfms.view.FeedbackView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Binh Nguyen on 21-Feb-18.
 */

@RestController
@RequestMapping("/api/feedbacks")
public class FeedbackAPI {

    //test new branch
    //test
    @Autowired
    private FeedbackService feedbackService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private ConductFeedbackService conductFeedbackService;

    @GetMapping
    private List<Feedback> getListTemplate(boolean isTemplate){
        return feedbackService.getListTemplate(isTemplate);
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
                return new Response(true, feedbackId + "");
            } catch (Exception ex) {
                return new Response(false, ex.getMessage());
            }

        }
        return new Response(false, "Xin kiểm tra lại feedback đã hợp lệ chưa");
    }

    @RequestMapping(value = "/deactive-template", method = RequestMethod.POST)
    @Transactional
    public Response deactiveTemplate(@RequestBody String templateIDS) {
        int templateID = Integer.parseInt(templateIDS);
            try {
                int result = feedbackService.deactiveTemplate(templateID);
                System.out.print("result: " + result);
                if (result > 0) {
                    return new Response(true);
                }
            } catch (Exception ex) {
                return new Response(false, ex.getMessage());
            }
        return new Response(false, "Xin kiểm tra lại feedback đã hợp lệ chưa");
    }

    @RequestMapping(value = "/modify-question", method = RequestMethod.POST)
    @Transactional
    public Response modifyQuestion(@RequestBody FeedbackUpdateModel model) {
        if(model.valid()) {
            try {
                //int feedbackId = feedbackService.save(model);
                int feedbackId = model.getId();
                List<Question> listExistedQuestion = questionService.findByFeedbackId(feedbackId);
                System.out.print("listExistedQuestion: " + listExistedQuestion.size());
                List<Integer> listModifyQuestionID = new ArrayList<>();
                for (UpdateQuestionModel question : model.getQuestions()) {
                    if (question.getQuestionId() >= 0) {
                        question.setFeedbackId(feedbackId);
                        questionService.updateQuestion(question);
                        listModifyQuestionID.add(question.getQuestionId());
                    } else {
                        AddQuestionModel addQuestion = new AddQuestionModel();
                        addQuestion.setFeedbackId(feedbackId);
                        addQuestion.setType(question.getType());
                      /*  if (question.getSuggestion() == null) {
                            addQuestion.setSuggestion("");
                        } else {
                            addQuestion.setSuggestion(question.getSuggestion());
                        }*/
                        addQuestion.setSuggestion(question.getSuggestion());
                        addQuestion.setCriteriaId(question.getCriteriaId());
                        addQuestion.setQuestionContent(question.getQuestionContent());
                        addQuestion.setRequired(question.isRequired());
                        addQuestion.setRequireOther(question.isRequireOther());
                        if (!question.getType().equals("Text") && !question.getType().equals("TextArea")) {
                            OptionCreateModel[] options = new OptionCreateModel[question.getOptionUpdateModels().length];
                            int i = 0;
                            for (OptionUpdateModel option : question.getOptionUpdateModels()) {
                                OptionCreateModel addOption = new OptionCreateModel();
                                addOption.setOptionContent(option.getOptionContent());
                                addOption.setPoint(option.getPoint());
                                //  addOption.setQuestionId(option.getQuestionId());
                                addOption.setQuestion(option.getQuestion());
                                options[i] = addOption;
                                i++;
                            }
                            addQuestion.setOptionCreateModel(options);
                        } else {
                            addQuestion.setOptionCreateModel(null);
                        }
                        int questionID = questionService.addQuestion(addQuestion);
                        listModifyQuestionID.add(questionID);
                    }
                }

                for (Question question : listExistedQuestion) {
                    if (!listModifyQuestionID.contains(question.getId()))     {
                        questionService.removeQuestion(question.getId());
                    }
                }

                return new Response(true, feedbackId + "");
            } catch (Exception ex) {
                return new Response(false, ex.getMessage());
            }

        }
        return new Response(false,"Xin kiểm tra lại feedback đã hợp lệ chưa");
    }

    @RequestMapping(value = "/modify-suggestion", method = RequestMethod.POST)
    @Transactional
    public Response modifySuggestion(@RequestBody FeedbackModifySuggestionModel model) {
        if(model.valid()) {
            try {
                //int feedbackId = feedbackService.save(model);
                int feedbackId = model.getId();
            //    List<Question> listExistedQuestion = questionService.findByFeedbackId(feedbackId);
                List<ModifySuggestionModel> listSuggestionQuestion = model.getQuestions();
             //   List<Integer> listModifyQuestionID = new ArrayList<>();
                for (ModifySuggestionModel question : listSuggestionQuestion) {
                      //  Question existedQuestion = questionService.findByQuestionID(question.getQuestionId());
                            questionService.modifySuggestion(question.getSuggestion(),question.getQuestionId());
                        //question.setFeedbackId(feedbackId);
                        //questionService.modifySuggestion(question);
                     //   listModifyQuestionID.add(question.getQuestionId());

                }

                return new Response(true, feedbackId + "");
            } catch (Exception ex) {
                return new Response(false, ex.getMessage());
            }

        }
        return new Response(false,"Xin kiểm tra lại feedback đã hợp lệ chưa");
    }

    @JsonView(FeedbackView.alertUserFeedbackView.class)
    @GetMapping("/undone-by-authorized-user")
    public ResponseEntity getListNotConductedFeedbackByAuthorizedUser() {
        return feedbackService.getNotificationData();
    }

    @JsonView(FeedbackView.alertUserFeedbackView.class)
    @GetMapping("/conduct")
    public ResponseEntity getListFeedbackOfAuthorizedUser() {
        System.out.println("getListFeedbackOfAuthorizedUser");
        return feedbackService.getNotConductedFeedbacksByUserId();
    }

    @JsonView(FeedbackView.alertUserFeedbackView.class)
    @GetMapping("/mobile/conduct-mobile")
    public ResponseEntity getListFeedbackOfAuthorizedUserMobile(@RequestHeader("username") String username) {
        System.out.println("getListFeedbackOfAuthorizedUser");
        return feedbackService.getNotConductedFeedbacksByUserIdMobile();
    }

    @JsonView(FeedbackView.conductFeedbackView.class)
    @GetMapping("/conduct/{id}")
    public Feedback conductFeedback(@PathVariable("id") int id) {
        return feedbackService.findFeedbackToConduct(id);
    }

    @JsonView(FeedbackView.conductFeedbackView.class)
    @GetMapping("/mobile/conduct-mobile/{id}")
    public Feedback conductFeedbackMobile(@RequestHeader("username") String username, @PathVariable("id") int id) {
        return feedbackService.findFeedbackToConductMobile(id);
    }


    @PostMapping("/switch-publish")
    public ResponseEntity switchPublishFeedback(@RequestParam(value = "id", defaultValue = "") int id) {
        return feedbackService.switchPublish(id);
    }


    @JsonView(FeedbackView.listView.class)
    @GetMapping("/list")
    public List<Feedback> getAllFeedback() {
        return feedbackService.findAllFeedbackNotTemplate();
    }


}
