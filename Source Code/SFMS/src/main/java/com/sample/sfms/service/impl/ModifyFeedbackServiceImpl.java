package com.sample.sfms.service.impl;

import com.sample.sfms.entity.*;
import com.sample.sfms.model.FeedbackDetailsModel;
import com.sample.sfms.model.ModifyFeedbackModel;
import com.sample.sfms.repository.*;
import com.sample.sfms.service.interf.ModifyFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.UnexpectedRollbackException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by MyPC on 23/02/2018.
 */
public class ModifyFeedbackServiceImpl implements ModifyFeedbackService {

    static Logger logger = Logger.getLogger(RoleServiceImpl.class.getName());

    @Autowired
    private FeedbackRepository feedbackRepo;

    @Autowired
    private MajorRepository majorRepo;

    @Autowired
    private CourseRepository courseRepo;

    @Autowired
    private ClazzRepository clazzRepo;

    @Autowired
    private DepartmentRepository departmentRepo;

    @Autowired
    private TypeRepository typeRepo;

    @Autowired
    private UserFilteringRepository userRepo;

    @Autowired
    private StudentClazzRepository studentClazzRepo;

    @Override
    public Feedback getFeedback(int id) {
        return feedbackRepo.findOne(id);
    }

    @Override
    public ResponseEntity<List<Feedback>> saveFeadbacks(ModifyFeedbackModel model) {
        ResponseEntity<List<Feedback>> response;
        try {
            List<Feedback> affected = new ArrayList<>();
            Feedback sharedTemplate = model.getFeedback(), tmp;
            Type t;
            String selectedusers;
            for (FeedbackDetailsModel detail : model.getSelectedObjs()) {
                t = detail.getType();
                selectedusers = "";
                tmp = sharedTemplate;
                switch (t.getDescription()) {
                    case "Major":
                        tmp.setMajorByMajorId(majorRepo.findOne(detail.getMajor().getId()));
                        break;
                    case "Course":
                        tmp.setCourseByCourseId(courseRepo.findOne(detail.getCourse().getId()));
                        break;
                    case "Clazz":
                        tmp.setClazzByClazzId(clazzRepo.findOne(detail.getClazz().getId()));
                        break;
                    case "Department":
                        tmp.setDepartmentByDepartmentId(departmentRepo.findOne(detail.getDepartment().getId()));
                        break;
                }
                tmp.setTypeByTypeId(t);
                for (User conductor : detail.getConductors()) {
                    selectedusers += conductor.getId() + "/";
                }
                tmp.setConductors(selectedusers.substring(0, selectedusers.length() - 1));
                for (User viewer : detail.getReportviewers()) {
                    selectedusers += viewer.getId() + "/";
                }
                tmp.setViewers(selectedusers.substring(0, selectedusers.length() - 1));
                affected.add(feedbackRepo.save(tmp));
            }
            response = new ResponseEntity<>(affected, HttpStatus.OK);
            return response;
        } catch (UnexpectedRollbackException e) {
            logger.log(Level.FINE, e.toString());
            response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            return response;
        }
    }

    @Override
    public ResponseEntity<ModifyFeedbackModel> addTarget(int typeId, int targetId, ModifyFeedbackModel model) {
        ResponseEntity<ModifyFeedbackModel> response;
        try {
            Type t = typeRepo.findOne(typeId);
            FeedbackDetailsModel detailModel = new FeedbackDetailsModel();
            detailModel.setType(t);
            switch (t.getDescription()) {
                case "Major":
                    detailModel.setMajor(majorRepo.findOne(targetId));
                    break;
                case "Course":
                    detailModel.setCourse(courseRepo.findOne(targetId));
                    break;
                case "Clazz":
                    detailModel.setClazz(clazzRepo.findOne(targetId));
                    break;
                case "Department":
                    detailModel.setDepartment(departmentRepo.findOne(targetId));
                    break;
            }
            detailModel.setConductors(autoGenerateConductors(detailModel));
            detailModel.setReportviewers(autoGenerateViewers(detailModel));
            model.getSelectedObjs().add(detailModel);
            response = new ResponseEntity<>(model, HttpStatus.OK);
            return response;
        } catch (UnexpectedRollbackException e) {
            logger.log(Level.FINE, e.toString());
            response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            return response;
        }
    }

    @Override
    public ResponseEntity<ModifyFeedbackModel> removeTarget(int typeId, int targetId, ModifyFeedbackModel model) {
        return null;
    }

    @Override
    public List<User> autoGenerateConductors(FeedbackDetailsModel detailModel) {
        List<User> conductors = new ArrayList<>();
        List<StudentClazz> studentClazzes = new ArrayList<>();
        User tmp;
        switch (detailModel.getType().getDescription()) {
            case "Major":
                detailModel.setConductors(userRepo.findByMajorByMajorId(detailModel.getMajor()));
                break;
            case "Course":
                Course course = detailModel.getCourse();
                List<Clazz> clazzesOfCourse = clazzRepo.findByCourseByCourseId(course);
                for (Clazz c : clazzesOfCourse) {
                    tmp = c.getUserByLecturerId();
                    if (!conductors.contains(tmp)) conductors.add(tmp);
                    studentClazzes.addAll(studentClazzRepo.findByClazzByClazzId(c));
                }
                for (StudentClazz sc : studentClazzes) {
                    tmp = sc.getUserByUserId();
                    if (!conductors.contains(tmp)) conductors.add(tmp);
                }
                break;
            case "Clazz":
                studentClazzes.addAll(studentClazzRepo.findByClazzByClazzId(detailModel.getClazz()));
                for (StudentClazz sc : studentClazzes) {
                    tmp = sc.getUserByUserId();
                    if (!conductors.contains(tmp)) conductors.add(tmp);
                }
                break;
            case "Department":

                break;
        }
        return conductors;
    }

    @Override
    public List<User> autoGenerateViewers(FeedbackDetailsModel model) {
        return null;
    }

    @Override
    public ResponseEntity<FeedbackDetailsModel> customizeConductors(FeedbackDetailsModel model, int[] conductorIds) {
        return null;
    }

    @Override
    public ResponseEntity<FeedbackDetailsModel> customizeViewers(FeedbackDetailsModel model, int[] viewerIds) {
        return null;
    }

    @Override
    public ResponseEntity<ModifyFeedbackModel> setStart(Date start, Feedback feedback) {
        return null;
    }

    @Override
    public ResponseEntity<ModifyFeedbackModel> setEnd(Date end, Feedback feedback) {
        return null;
    }


}
