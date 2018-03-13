package com.sample.sfms.api;

//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.core.json.UTF8JsonGenerator;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.sfms.entity.Feedback;
//import com.sample.sfms.entity.Role;
//import com.sample.sfms.entity.Semester;
import com.sample.sfms.entity.Semester;
//import com.sample.sfms.model.ModifyFeedbackModel;
import com.sample.sfms.service.interf.ModifyFeedbackService;
import com.sample.sfms.view.TargetView;
import com.sample.sfms.view.SemesterView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.ModelAndView;
//import sun.nio.cs.ISO_8859_2;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MyPC on 24/02/2018.
 */

@RestController
@SessionAttributes("MFModel")
@RequestMapping("/api/modify-feedback")
public class ModifyFeedbackAPI {
    @Autowired
    ModifyFeedbackService modifyService;

    public static ObjectMapper om = new ObjectMapper();
    private String title;
    private HttpSession session;

    @GetMapping("/get/{id}")
    private ResponseEntity<Feedback> getFeedback(@PathVariable("id") int id) {
        return modifyService.getFeedback(id);
    }

    @JsonView(TargetView.basicTargetView.class)
    @GetMapping("/list/targets/clazzes")
    private ResponseEntity listClazzesTargets(HttpSession session){
        if(session.getAttribute("targetIds")==null)return new ResponseEntity(HttpStatus.BAD_REQUEST);
        Feedback f = modifyService.getFeedback(Integer.parseInt(session.getAttribute("id").toString())).getBody();
        if(f.getTypeByTypeId()==null) return new ResponseEntity(new ArrayList<>(),HttpStatus.OK);
        switch (f.getTypeByTypeId().getDescription()){
            case "Chuyên ngành": return new ResponseEntity(new ArrayList<>(),HttpStatus.OK);
            case "Môn học": return new ResponseEntity(new ArrayList<>(), HttpStatus.OK);
            case "Lớp": return modifyService.loadClazzTargets((List<Integer>)session.getAttribute("targetIds"));
            case "Phòng ban": return new ResponseEntity(new ArrayList<>(), HttpStatus.OK);
            default: return new ResponseEntity(new ArrayList<>(), HttpStatus.OK);
        }
    }

    @JsonView(TargetView.basicTargetView.class)
    @GetMapping("/list/targets/departments")
    private ResponseEntity listDepartmentTargets(HttpSession session) throws JsonProcessingException {
        if(session.getAttribute("targetIds")==null)return new ResponseEntity(HttpStatus.BAD_REQUEST);
        Feedback f = modifyService.getFeedback(Integer.parseInt(session.getAttribute("id").toString())).getBody();
        if(f.getTypeByTypeId()==null) return new ResponseEntity(new ArrayList<>(),HttpStatus.OK);
        switch (f.getTypeByTypeId().getDescription()){
            case "Chuyên ngành": return new ResponseEntity(new ArrayList<>(),HttpStatus.OK);
            case "Môn học": return new ResponseEntity(new ArrayList<>(),HttpStatus.OK);
            case "Lớp": return new ResponseEntity(new ArrayList<>(),HttpStatus.OK);
            case "Phòng ban": return modifyService.loadDepartmentTargets((List<Integer>)session.getAttribute("targetIds"));
            default: return new ResponseEntity(new ArrayList<>(),HttpStatus.OK);
        }

    }
    @JsonView(TargetView.basicTargetView.class)
    @GetMapping("/list/targets/majors")
    private ResponseEntity listMajorTargets(HttpSession session) throws JsonProcessingException {
        if(session.getAttribute("targetIds")==null)return new ResponseEntity(HttpStatus.BAD_REQUEST);
        Feedback f = modifyService.getFeedback(Integer.parseInt(session.getAttribute("id").toString())).getBody();
        if(f.getTypeByTypeId()==null) return new ResponseEntity(new ArrayList<>(),HttpStatus.OK);
        List response;
        switch (f.getTypeByTypeId().getDescription()){
            case "Chuyên ngành": return modifyService.loadMajorTargets((List<Integer>)session.getAttribute("targetIds"));
            case "Môn học":return new ResponseEntity(new ArrayList<>(),HttpStatus.OK);
            case "Lớp": return new ResponseEntity(new ArrayList<>(),HttpStatus.OK);
            case "Phòng ban": return new ResponseEntity(new ArrayList<>(),HttpStatus.OK);
            default: return new ResponseEntity(new ArrayList<>(),HttpStatus.OK);
        }
//        return ObjToJson(response);
    }
    @JsonView(TargetView.basicTargetView.class)
    @GetMapping("/list/targets/courses")
    private ResponseEntity listCourseTargets(HttpSession session) throws JsonProcessingException {
        if(session.getAttribute("targetIds")==null)return new ResponseEntity(HttpStatus.BAD_REQUEST);
        Feedback f = modifyService.getFeedback(Integer.parseInt(session.getAttribute("id").toString())).getBody();
        if(f.getTypeByTypeId()==null) return new ResponseEntity(HttpStatus.OK);
        switch (f.getTypeByTypeId().getDescription()){
//            case "Chuyên ngành": return new ResponseEntity(new ArrayList<>(),HttpStatus.OK);
            case "Môn học": return modifyService.loadCourseTargets((List<Integer>)session.getAttribute("targetIds"));
//            case "Lớp": return new ResponseEntity(HttpStatus.OK);
//            case "Phòng ban": return new ResponseEntity(HttpStatus.OK);
            default: return new ResponseEntity(new ArrayList<>(),HttpStatus.OK);
        }
//        return ObjToJson(response);
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
    private ResponseEntity editTitle(@RequestParam("title") String title, HttpSession session) {
        Feedback f = modifyService.getFeedback(Integer.parseInt(session.getAttribute("id").toString())).getBody();
        f.setFeedbackName(title);
        return modifyService.saveFeedback(f);
    }

    @PutMapping("/description")
    private ResponseEntity<Feedback> editDescription(@RequestParam("description") String description, HttpSession session) {
        Feedback f = modifyService.getFeedback(Integer.parseInt(session.getAttribute("id").toString())).getBody();
        f.setFeedbackDes(description);
        return modifyService.saveFeedback(f);
    }

    @PutMapping("/start")
    private ResponseEntity<Feedback> editStart(@RequestParam("startdate") String startdate, HttpSession session) {
        try {
            return modifyService.setStart(new SimpleDateFormat("yyyy-MM-dd").parse(startdate), (int) session.getAttribute("id"));
        } catch (ParseException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/end")
    private ResponseEntity<Feedback> editEnd(@RequestParam("enddate") String enddate, HttpSession session) {
        try {
            return modifyService.setEnd(new SimpleDateFormat("yyyy-MM-dd").parse(enddate), (int) session.getAttribute("id"));
        } catch (ParseException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @JsonView(SemesterView.basicSemesterView.class)
    @PutMapping("/semester")
    private ResponseEntity<Semester> editSemester(@RequestBody Semester semester, HttpSession session) {
        return  modifyService.updateSemester(semester.getId(),(int) session.getAttribute("id"));
    }

    @PutMapping("/type")
    private ResponseEntity editType(@RequestParam("typeId") int typeId, HttpSession session) {
        modifyService.deleteFeedbacks((List<Integer>) session.getAttribute("targetIds"));
        return modifyService.updateType(typeId, (int) session.getAttribute("id"));
    }

    @PostMapping("/add/target")
    private ResponseEntity<Integer> addTarget(@RequestParam("id") int id, HttpSession session) {
        List<Integer> targetIds = (List<Integer>) session.getAttribute("targetIds");
        if(targetIds==null)targetIds = new ArrayList<>();
        Feedback response = modifyService.addTarget((int) session.getAttribute("id"), id, targetIds).getBody();
        if (!targetIds.contains(response.getId())) targetIds.add(response.getId());
        session.setAttribute("targetIds", targetIds);
        return new ResponseEntity<>(response.getMajorByMajorId().getId(),
                HttpStatus.OK);
    }

    @PutMapping("/remove/target")
    private ResponseEntity<Integer> removeTarget(@RequestParam("id") int id, HttpSession session) {
        List<Integer> targetIds = (List<Integer>) session.getAttribute("targetIds");
        Feedback response = modifyService.addTarget((int) session.getAttribute("id"), id, targetIds).getBody();
        if (!targetIds.contains(response.getId())) targetIds.add(response.getId());
        session.setAttribute("targetIds", targetIds);
        return new ResponseEntity<>(response.getMajorByMajorId().getId(),
                HttpStatus.OK);
    }

    @PostMapping("/add/conductor")
    private ResponseEntity<Integer> addConductor(@RequestParam("id") int id, HttpSession session) {
        List<Integer> targetIds = (List<Integer>) session.getAttribute("targetIds");
        Feedback response = modifyService.addTarget((int) session.getAttribute("id"), id, targetIds).getBody();
        if (!targetIds.contains(response.getId())) targetIds.add(response.getId());
        session.setAttribute("targetIds", targetIds);
        return new ResponseEntity<>(response.getMajorByMajorId().getId(),
                HttpStatus.OK);
    }

    @PutMapping("/add/conductor")
    private ResponseEntity<Integer> removeConductor(@RequestParam("id") int id, HttpSession session) {
        List<Integer> targetIds = (List<Integer>) session.getAttribute("targetIds");
        Feedback response = modifyService.addTarget((int) session.getAttribute("id"), id, targetIds).getBody();
        if (!targetIds.contains(response.getId())) targetIds.add(response.getId());
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

    String ObjToJson(Object o) throws JsonProcessingException {
        return om.writerWithDefaultPrettyPrinter().writeValueAsString(o);
    }
}
