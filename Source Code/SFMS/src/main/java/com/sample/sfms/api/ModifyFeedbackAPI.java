package com.sample.sfms.api;

import com.sample.sfms.entity.Feedback;
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

    @GetMapping("/{id}")
    private ResponseEntity<Feedback> getFeedback(@PathVariable("id") int id){
        return modifyService.getFeedback(id);
    }

    @PostMapping
    private ResponseEntity<Feedback> createFeedback(){
        return modifyService.createEmptyFeedback();
    }

    @PostMapping("/{id}")
    private ResponseEntity<Feedback> createFeedback(@PathVariable("id") int id){
        return modifyService.createFeedbackFromTemplate(id);
    }

    @PutMapping
    private ResponseEntity<Feedback> updateTemplate(@RequestParam("MFModel") ModifyFeedbackModel MFModel){
        return modifyService.updateTemplate(MFModel);
    }

    @DeleteMapping
    private ResponseEntity<Feedback> deleteFeedback(@RequestParam("deletedId") int id){
        return modifyService.deleteFeedback(id);
    }

//    @DeleteMapping
//    private ResponseEntity<ModifyFeedbackModel> cancel


//    @PutMapping
//    private ResponseEntity<ModifyFeedbackModel> saveFeedbackModel (@RequestParam)

}
