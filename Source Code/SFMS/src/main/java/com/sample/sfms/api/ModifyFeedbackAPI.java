package com.sample.sfms.api;

import com.sample.sfms.entity.Feedback;
import com.sample.sfms.entity.Role;
import com.sample.sfms.entity.Semester;
import com.sample.sfms.entity.Type;
import com.sample.sfms.model.ModifyFeedbackModel;
import com.sample.sfms.service.interf.ModifyFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Date;
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
class ModifyFeedbackAPI {
    @Autowired
    ModifyFeedbackService modifyService;

    @GetMapping("/get/{id}")
    private ResponseEntity<Feedback> getFeedback(@PathVariable("id") int id) {
        return modifyService.getFeedback(id);
    }

    @PostMapping
    private ResponseEntity<Feedback> createFeedback(HttpSession session, @RequestParam("title") String title, @RequestParam("description") String description) {
        ResponseEntity<Feedback> response = modifyService.createEmptyFeedback(title, description);
        session.setAttribute("id", response.getBody().getId());
        return response;
    }

    @PostMapping("/create/{id}")
    private ResponseEntity<Feedback> createFeedbackFromTemplate(@PathVariable("id") int id, HttpSession session) {
        ResponseEntity<Feedback> response = modifyService.createFeedbackFromTemplate(id);
        session.setAttribute("id", response.getBody().getId());
        return response;
    }

    @PutMapping("/title")
    private ResponseEntity<String> editTitle(@RequestParam("title") String title, HttpSession session) {
        Feedback f = modifyService.getFeedback(Integer.parseInt(session.getAttribute("id").toString())).getBody();
        f.setFeedbackName(title);
        return new ResponseEntity<>(modifyService.saveFeedback(f).getBody().getFeedbackName(), HttpStatus.OK);
    }

    @PutMapping("/description")
    private ResponseEntity<String> editDescription(@RequestParam("description") String description, HttpSession session) {
        Feedback f = modifyService.getFeedback(Integer.parseInt(session.getAttribute("id").toString())).getBody();
        f.setFeedbackDes(description);
        return new ResponseEntity<>(modifyService.saveFeedback(f).getBody().getFeedbackDes(), HttpStatus.OK);
    }

    @PutMapping("/start")
    private ResponseEntity<String> editStart(@RequestParam("startdate") Date startdate, HttpSession session) {
        return new ResponseEntity<>(modifyService.setStart(startdate,(int) session.getAttribute("id")).getBody(), HttpStatus.OK);
    }

    @PutMapping("/end")
    private ResponseEntity<String> editEnd(@RequestParam("enddate") Date enddate, HttpSession session) {
        Feedback f = modifyService.getFeedback((int) session.getAttribute("id")).getBody();
        f.setEndDate(enddate);
        return new ResponseEntity<>(modifyService.setEnd(enddate,(int) session.getAttribute("id")).getBody(), HttpStatus.OK);
    }

    @PutMapping("/semester")
    private ResponseEntity<Semester> editSemester(@RequestParam("semesterId") int semesterId, HttpSession session) {
        return new ResponseEntity<>(
                modifyService.updateSemester(semesterId,
                        (int) session.getAttribute("id")).getBody().getSemesterBySemesterId(),
                HttpStatus.OK);
    }

    @PutMapping("/type")
    private ResponseEntity<Type> editType(@RequestParam("typeId") int typeId, HttpSession session) {
        modifyService.deleteFeedbacks((List<Integer>)session.getAttribute("targetIds"));
        return new ResponseEntity<Type>(
                modifyService.updateType(typeId,
                        (int) session.getAttribute("id")).getBody().getTypeByTypeId(),
                HttpStatus.OK);
    }

    @PostMapping("/add-target")
    private ResponseEntity<Integer> addTarget(@RequestParam("id") int id, HttpSession session) {
        List<Integer> targetIds = (List<Integer>)session.getAttribute("targetIds");
        Feedback response = modifyService.addTarget((int)session.getAttribute("id"),id, targetIds).getBody();
        if(!targetIds.contains(response.getId()))targetIds.add(response.getId());
        session.setAttribute("targetIds", targetIds);
        return new ResponseEntity<>(response.getMajorByMajorId().getId(),
                HttpStatus.OK);
    }



//    @PostMapping("/add-conductor")
//    private ResponseEntity<Integer> addCourseTarget(@RequestParam("semesterId") int typeId, HttpSession session) {
//        modifyService.deleteFeedbacks((List<Integer>)session.getAttribute("targetIds"));
//        return new ResponseEntity<Type>(
//                modifyService.updateType(typeId,
//                        (int) session.getAttribute("id")).getBody().getTypeByTypeId(),
//                HttpStatus.OK);
//    }

    @DeleteMapping
    private ResponseEntity<Feedback> deleteFeedback(@RequestParam("deletedId") int id) {
        return modifyService.deleteFeedback(id);
    }

//    @DeleteMapping
//    private ResponseEntity<ModifyFeedbackModel> cancel


//    @PutMapping
//    private ResponseEntity<ModifyFeedbackModel> saveFeedbackModel (@RequestParam)

}
