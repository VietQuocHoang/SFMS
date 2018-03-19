package com.sample.sfms.api;

//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.core.json.UTF8JsonGenerator;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.sfms.entity.Clazz;
import com.sample.sfms.entity.Feedback;
//import com.sample.sfms.entity.Role;
//import com.sample.sfms.entity.Semester;
import com.sample.sfms.entity.Semester;
//import com.sample.sfms.model.ModifyFeedbackModel;
import com.sample.sfms.entity.Type;
import com.sample.sfms.model.FilteringModel;
import com.sample.sfms.service.interf.ModifyFeedbackService;
import com.sample.sfms.view.FeedbackView;
import com.sample.sfms.view.TargetView;
import com.sample.sfms.view.SemesterView;
import com.sample.sfms.view.TypeView;
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

    @JsonView({FeedbackView.overview.class})
    @GetMapping("/get/{id}")
    private ResponseEntity<Feedback> getFeedback(@PathVariable("id") int id) {
        return modifyService.getFeedback(id);
    }

    @JsonView({FeedbackView.overview.class})
    @GetMapping("/get")
    private ResponseEntity<Feedback> getFeedback(HttpSession session) {
        return modifyService.getFeedback((int) session.getAttribute("id"));
    }

    @JsonView(TargetView.basicClazzView.class)
    @GetMapping("/list/targets/clazzes")
    private ResponseEntity listClazzesTargets(HttpSession session) {
        if (session.getAttribute("targetIds") == null) return new ResponseEntity(HttpStatus.BAD_REQUEST);
        Feedback f = modifyService.getFeedback(Integer.parseInt(session.getAttribute("id").toString())).getBody();
        if (f.getTypeByTypeId() == null) return new ResponseEntity(new ArrayList<>(), HttpStatus.OK);
        switch (f.getTypeByTypeId().getDescription()) {
//            case "Chuyên ngành": return new ResponseEntity(new ArrayList<>(),HttpStatus.OK);
//            case "Môn học": return new ResponseEntity(new ArrayList<>(), HttpStatus.OK);
            case "Lớp":
                return modifyService.loadClazzTargets((List<Integer>) session.getAttribute("targetIds"));
//            case "Phòng ban": return new ResponseEntity(new ArrayList<>(), HttpStatus.OK);
            default:
                return new ResponseEntity(new ArrayList<>(), HttpStatus.OK);
        }
    }

    @JsonView(TargetView.basicDepartmentView.class)
    @GetMapping("/list/targets/departments")
    private ResponseEntity listDepartmentTargets(HttpSession session) throws JsonProcessingException {
        if (session.getAttribute("targetIds") == null) return new ResponseEntity(HttpStatus.BAD_REQUEST);
        Feedback f = modifyService.getFeedback(Integer.parseInt(session.getAttribute("id").toString())).getBody();
        if (f.getTypeByTypeId() == null) return new ResponseEntity(new ArrayList<>(), HttpStatus.OK);
        switch (f.getTypeByTypeId().getDescription()) {
//            case "Chuyên ngành": return new ResponseEntity(new ArrayList<>(),HttpStatus.OK);
//            case "Môn học": return new ResponseEntity(new ArrayList<>(),HttpStatus.OK);
//            case "Lớp": return new ResponseEntity(new ArrayList<>(),HttpStatus.OK);
            case "Phòng ban":
                return modifyService.loadDepartmentTargets((List<Integer>) session.getAttribute("targetIds"));
            default:
                return new ResponseEntity(new ArrayList<>(), HttpStatus.OK);
        }

    }

    @JsonView(TargetView.basicMajorView.class)
    @GetMapping("/list/targets/majors")
    private ResponseEntity listMajorTargets(HttpSession session) throws JsonProcessingException {
        if (session.getAttribute("targetIds") == null) return new ResponseEntity(HttpStatus.BAD_REQUEST);
        Feedback f = modifyService.getFeedback(Integer.parseInt(session.getAttribute("id").toString())).getBody();
        if (f.getTypeByTypeId() == null) return new ResponseEntity(new ArrayList<>(), HttpStatus.OK);
        List response;
        switch (f.getTypeByTypeId().getDescription()) {
            case "Chuyên ngành":
                return modifyService.loadMajorTargets((List<Integer>) session.getAttribute("targetIds"));
//            case "Môn học":return new ResponseEntity(new ArrayList<>(),HttpStatus.OK);
//            case "Lớp": return new ResponseEntity(new ArrayList<>(),HttpStatus.OK);
//            case "Phòng ban": return new ResponseEntity(new ArrayList<>(),HttpStatus.OK);
            default:
                return new ResponseEntity(new ArrayList<>(), HttpStatus.OK);
        }
//        return ObjToJson(response);
    }

    @JsonView(TargetView.basicCourseView.class)
    @GetMapping("/list/targets/courses")
    private ResponseEntity listCourseTargets(HttpSession session) throws JsonProcessingException {
        if (session.getAttribute("targetIds") == null) return new ResponseEntity(HttpStatus.BAD_REQUEST);
        Feedback f = modifyService.getFeedback(Integer.parseInt(session.getAttribute("id").toString())).getBody();
        if (f.getTypeByTypeId() == null) return new ResponseEntity(HttpStatus.OK);
        switch (f.getTypeByTypeId().getDescription()) {
//            case "Chuyên ngành": return new ResponseEntity(new ArrayList<>(),HttpStatus.OK);
            case "Môn học":
                return modifyService.loadCourseTargets((List<Integer>) session.getAttribute("targetIds"));
//            case "Lớp": return new ResponseEntity(HttpStatus.OK);
//            case "Phòng ban": return new ResponseEntity(HttpStatus.OK);
            default:
                return new ResponseEntity(new ArrayList<>(), HttpStatus.OK);
        }
//        return ObjToJson(response);
    }

    @JsonView(TargetView.basicClazzView.class)
    @GetMapping("/list/clazzes")
    private ResponseEntity listClazzes(FilteringModel model, HttpSession session) {
        String majorkey = model.majorName==null?"":model.majorName;
        String coursekey = model.courseName==null?"":model.courseName;
        String semesterkey = model.semesterTitle==null?"":model.semesterTitle;
        String lecturerkey  = model.lecturerName==null?"":model.lecturerName;
        return modifyService.filterClazz(majorkey,coursekey,semesterkey,lecturerkey);
    }

    @JsonView(TargetView.basicClazzView.class)
    @GetMapping("/list/clazzes/{major}/{course}/{semester}/{lecturer}")
    private ResponseEntity listClazzes(@PathVariable("major") String major,@PathVariable("course") String course,
                                       @PathVariable("semester")String semester, @PathVariable("lecturer")String lecturer, HttpSession session) {

        int majorKey = Integer.parseInt(major);
        int courseKey = Integer.parseInt(course);
        int semesterKey = Integer.parseInt(semester);
        int lecturerKey = Integer.parseInt(lecturer);
        return modifyService.filterClazz(majorKey,courseKey,semesterKey,lecturerKey);
    }

    @PostMapping("/create/{id}")
    private ResponseEntity<Feedback> createFeedbackFromTemplate(@PathVariable("id") int id, HttpSession session) {
        ResponseEntity<Feedback> response = modifyService.createFeedbackFromTemplate(id);
        session.setAttribute("id", response.getBody().getId());
        return response;
    }

    @JsonView({FeedbackView.overview.class})
    @PutMapping("/title")
    private ResponseEntity editTitle(@RequestBody Feedback feedback, HttpSession session) {
        Feedback f = modifyService.getFeedback(Integer.parseInt(session.getAttribute("id").toString())).getBody();
        f.setFeedbackName(feedback.getFeedbackName());
        return modifyService.saveFeedback(f);
    }

    @JsonView({FeedbackView.overview.class})
    @PutMapping("/description")
    private ResponseEntity<Feedback> editDescription(@RequestBody Feedback feedback, HttpSession session) {
        Feedback f = modifyService.getFeedback(Integer.parseInt(session.getAttribute("id").toString())).getBody();
        f.setFeedbackDes(feedback.getFeedbackDes());
        return modifyService.saveFeedback(f);
    }

    @JsonView({FeedbackView.overview.class})
    @PutMapping("/start")
    private ResponseEntity<Feedback> editStart(@RequestBody Feedback feedback, HttpSession session) {
        try {
            return modifyService.setStart(feedback.getStartDate(), (int) session.getAttribute("id"));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @JsonView({FeedbackView.overview.class})
    @PutMapping("/end")
    private ResponseEntity<Feedback> editEnd(@RequestBody Feedback feedback, HttpSession session) {
        try {
            return modifyService.setEnd(feedback.getEndDate(), (int) session.getAttribute("id"));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @JsonView(SemesterView.basicSemesterView.class)
    @PutMapping("/semester")
    private ResponseEntity<Semester> editSemester(@RequestBody Semester semester, HttpSession session) {
        return modifyService.updateSemester(semester.getId(), (int) session.getAttribute("id"));
    }

    @JsonView({FeedbackView.overview.class})
    @PutMapping("/type")
    private ResponseEntity editType(@RequestBody Type type, HttpSession session) {
//        modifyService.deleteFeedbacks((List<Integer>) session.getAttribute("targetIds"));
        session.setAttribute("targetIds", new ArrayList<>());
        return modifyService.updateType(type.getId(), (int) session.getAttribute("id"));
    }

    @PostMapping("/add/target")
    private ResponseEntity addTarget(@RequestParam("id") int id, HttpSession session) {
        List<Integer> targetIds = (List<Integer>) session.getAttribute("targetIds");
        if (targetIds == null) targetIds = new ArrayList<>();
        ResponseEntity response = modifyService.addTarget((int) session.getAttribute("id"), id, targetIds);
        session.setAttribute("targetIds", response.getBody());
        return response;
    }

    @PutMapping("/remove/target")
    private ResponseEntity removeClazzTarget(@RequestBody Clazz c, HttpSession session) {
        List<Integer> targetIds = (List<Integer>) session.getAttribute("targetIds");
        ResponseEntity response = modifyService.removeTarget(c.getId(), targetIds);
        session.setAttribute("targetIds", response.getBody());
        return response;
    }

    @PostMapping("/add/conductor")
    private ResponseEntity<Integer> addConductor(@RequestParam("id") int id, HttpSession session) {
        return null;
    }

    @DeleteMapping("/remove/conductor")
    private ResponseEntity<Integer> removeConductor(@RequestParam("id") int id, HttpSession session) {
        return null;
    }


//    @PostMapping("/add-conductor")
//    private ResponseEntity<Integer> addCourseTarget(@RequestParam("semesterId") int typeId, HttpSession session) {
//        modifyService.deleteFeedbacks((List<Integer>)session.getAttribute("targetIds"));
//        return new ResponseEntity<Type>(
//                modifyService.updateType(typeId,
//                        (int) session.getAttribute("id")).getBody().getTypeByTypeId(),
//                HttpStatus.OK);
//    }

    @PutMapping("/save/option/{opt}")
    private ResponseEntity saveFeedback(@PathVariable("opt") int opt, HttpSession session) {
        switch (opt) {
            case 1:
                return modifyService.savePublishFeadbacks((int) session.getAttribute("id"),
                        (List<Integer>) session.getAttribute("targetIds"));
            case 2:
                return modifyService.saveTemplateFeadback((int) session.getAttribute("id"),
                        (List<Integer>) session.getAttribute("targetIds"));
            case 3:
                return modifyService.updateSelectedTemplate((int) session.getAttribute("id"),
                        (List<Integer>) session.getAttribute("targetIds"));
            default:
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete")
    private ResponseEntity<Feedback> deleteFeedback(@RequestBody Feedback feedback) {
        return modifyService.deleteFeedback(feedback.getId());
    }

    @DeleteMapping("/cancel")
    private ResponseEntity canceProcess(HttpSession session) {
        return modifyService.cancelProcess((int) session.getAttribute("id"), (List<Integer>) session.getAttribute("targetIds"));
    }

    String ObjToJson(Object o) throws JsonProcessingException {
        return om.writerWithDefaultPrettyPrinter().writeValueAsString(o);
    }
}
