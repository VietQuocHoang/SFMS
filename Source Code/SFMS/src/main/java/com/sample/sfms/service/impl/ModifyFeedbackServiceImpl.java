package com.sample.sfms.service.impl;

import com.sample.sfms.entity.Feedback;
import com.sample.sfms.entity.Type;
import com.sample.sfms.entity.User;
import com.sample.sfms.model.FeedbackDetailsModel;
import com.sample.sfms.model.ModifyFeedbackModel;
import com.sample.sfms.repository.*;
import com.sample.sfms.service.interf.ModifyFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
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
                        tmp.setMajorByMajorId(majorRepo.findOne(detail.getTargetedId()));
                        break;
                    case "Course":
                        tmp.setCourseByCourseId(courseRepo.findOne(detail.getTargetedId()));
                        break;
                    case "Clazz":
                        tmp.setClazzByClazzId(clazzRepo.findOne(detail.getTargetedId()));
                        break;
                    case "Department":
                        tmp.setDepartmentByDepartmentId(departmentRepo.findOne(detail.getTargetedId()));
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
                    detailModel.setTargetedId(majorRepo.findOne(targetId).getId());
                    break;
                case "Course":
                    detailModel.setTargetedId(courseRepo.findOne(targetId).getId());
                    break;
                case "Clazz":
                    detailModel.setTargetedId(clazzRepo.findOne(targetId).getId());
                    break;
                case "Department":
                    detailModel.setTargetedId(departmentRepo.findOne(targetId).getId());
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
        switch (detailModel.getType().getDescription()){
            case "Major":

                break;
            case "Course":

                break;
            case "Clazz":

                break;
            case "Department":

                break;
        }
        return null;
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
