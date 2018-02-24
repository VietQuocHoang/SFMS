package com.sample.sfms.api;

import com.sample.sfms.entity.Role;
import com.sample.sfms.model.ModifyFeedbackModel;
import com.sample.sfms.service.interf.ModifyFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by MyPC on 24/02/2018.
 */
@RestController
@SessionAttributes("MFModel")//modify feedback model
@RequestMapping("/api/modify-feedback-targets")
class ModifyFeedbackObjsAPI {
    @Autowired
    ModifyFeedbackService modifyService;


}
@RestController
@SessionAttributes("MFModel")
@RequestMapping("/api/modify-feedback")
class  ModifyFeedbackAPI{
    @Autowired
    ModifyFeedbackService modifyService;

    @GetMapping
    private ModifyFeedbackModel getMFModel(HttpSession session){
        return (ModifyFeedbackModel) session.getAttribute("MFModel");
    }

    @PostMapping
    private ResponseEntity<ModifyFeedbackModel> createFeedbackModel(@RequestParam("templateId") int templateId, HttpSession session){
        ResponseEntity<ModifyFeedbackModel> response;
        if(templateId==0) response = modifyService.createEmptyFeedback();
        else response = modifyService.createFeedbackFromTemplate(templateId);
        session.setAttribute("MFModel", response);
        return response;
    }

//    @DeleteMapping
//    private ResponseEntity<ModifyFeedbackModel> cancel


//    @PutMapping
//    private ResponseEntity<ModifyFeedbackModel> saveFeedbackModel (@RequestParam)

}