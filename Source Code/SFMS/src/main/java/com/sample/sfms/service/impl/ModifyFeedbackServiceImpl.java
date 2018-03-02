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
import org.springframework.stereotype.Service;
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
@Service("ModifyFeedbackService")
public class ModifyFeedbackServiceImpl implements ModifyFeedbackService {

    static Logger logger = Logger.getLogger(ModifyFeedbackServiceImpl.class.getName());

    @Autowired
    private FeedbackRepository feedbackRepo;

    @Autowired
    private MajorRepository majorRepo;

    private MajorCourseRepository majorCourseRepo;
    @Autowired
    private CourseRepository courseRepo;

    @Autowired
    private ClazzRepository clazzRepo;

    @Autowired
    private DepartmentRepository departmentRepo;

    @Autowired
    private TypeRepository typeRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private StudentClazzRepository studentClazzRepo;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private UserFilteringRepository userFilteringRepo;

    @Override
    public ModifyFeedbackModel getFeedback(int id) {
        Feedback fb = feedbackRepo.findOne(id);
        String[] conductorIds = fb.getConductors().split("/"), viewerIds = fb.getViewers().split("/");
        List<User> conductors = new ArrayList<>(), viewers = new ArrayList<>();
        User tmp;
        if (conductorIds != null) {
            for (String conductorId : conductorIds) {
                tmp = userRepo.findOne(Integer.parseInt(conductorId));
                if (tmp != null) conductors.add(tmp);
            }
        }
        if (viewerIds != null) {
            for (String viewerId : viewerIds) {
                tmp = userRepo.findOne(Integer.parseInt(viewerId));
                if (tmp != null) viewers.add(tmp);
            }
        }
        List<FeedbackDetailsModel> details = new ArrayList<>();
        switch (fb.getTypeByTypeId().getDescription()) {
            case "Major":
                details.add(new FeedbackDetailsModel(conductors, viewers, fb.getMajorByMajorId(), null, null, null, fb.getTypeByTypeId()));
                break;
            case "Course":
                details.add(new FeedbackDetailsModel(conductors, viewers, null, fb.getCourseByCourseId(), null, null, fb.getTypeByTypeId()));
                break;
            case "Clazz":
                details.add(new FeedbackDetailsModel(conductors, viewers, null, null, fb.getClazzByClazzId(), null, fb.getTypeByTypeId()));
                break;
            case "Department":
                details.add(new FeedbackDetailsModel(conductors, viewers, null, null, null, fb.getDepartmentByDepartmentId(), fb.getTypeByTypeId()));
                break;
            default:
                break;
        }
        return new ModifyFeedbackModel(fb, details);
    }

    @Override
    public ModifyFeedbackModel createFeedbackFromTemplate(int id) {

        try {
            return new ModifyFeedbackModel(feedbackRepo.findOne(id), null);
        } catch (UnexpectedRollbackException e) {
            logger.log(Level.FINE, e.toString());
            return null;
        }
    }


    @Override
    public ModifyFeedbackModel createEmptyFeedback() {
        return new ModifyFeedbackModel();
    }

    @Override
    public ResponseEntity<List<Feedback>> saveNewFeadbacks(ModifyFeedbackModel model) {
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
                    default:
                        break;
                }
                tmp.setTypeByTypeId(t);
                for (User conductor : detail.getConductors()) {
                    selectedusers += conductor.getId() + "/";
                }
                tmp.setConductors(selectedusers.substring(0, selectedusers.length() - 1));
                selectedusers = "";
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
    public ResponseEntity<Feedback> updateTemplate(ModifyFeedbackModel model) {
        try {
            if (feedbackRepo.exists(model.getFeedback().getId())) {
                model.getFeedback().setConductors("");
                model.getFeedback().setViewers("");
                feedbackRepo.save(model.getFeedback());
                return new ResponseEntity<>(HttpStatus.OK);
            } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (UnexpectedRollbackException e) {
            logger.log(Level.FINE, e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Feedback> deleteFeedback(int id) {
        try {
            feedbackRepo.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (UnexpectedRollbackException e) {
            logger.log(Level.FINE, e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
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
                default:
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
        try {
            List<FeedbackDetailsModel> details = model.getSelectedObjs();
            Type t = typeRepo.findOne(typeId);
            for (FeedbackDetailsModel dm : details) {
                if (dm.getType().equals(t))
                    switch (t.getDescription()) {
                        case "Major":
                            if (dm.getMajor().getId() == targetId) {
                                details.remove(dm);
                                model.setSelectedObjs(details);
                                return new ResponseEntity<>(model, HttpStatus.OK);
                            }
                            break;
                        case "Course":
                            if (dm.getCourse().getId() == targetId) {
                                details.remove(dm);
                                model.setSelectedObjs(details);
                                return new ResponseEntity<>(model, HttpStatus.OK);
                            }
                            break;
                        case "Clazz":
                            if (dm.getClazz().getId() == targetId) {
                                details.remove(dm);
                                model.setSelectedObjs(details);
                                return new ResponseEntity<>(model, HttpStatus.OK);
                            }
                            break;
                        case "Department":
                            if (dm.getDepartment().getId() == targetId) {
                                details.remove(dm);
                                model.setSelectedObjs(details);
                                return new ResponseEntity<>(model, HttpStatus.OK);
                            }
                            break;
                        default:
                            break;
                    }
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (UnexpectedRollbackException e) {
            logger.log(Level.FINE, e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public List<User> autoGenerateConductors(FeedbackDetailsModel detailModel) {
        List<User> conductors = new ArrayList<>();
        List<StudentClazz> studentClazzes = new ArrayList<>();
        User tmp;
        String specialDepartment = "IT/Y tế/Thư Viện";
        switch (detailModel.getType().getDescription()) {
            case "Major":
                conductors = userFilteringRepo.findByMajorByMajorId(detailModel.getMajor());
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
                if (specialDepartment.contains(detailModel.getDepartment().getName())) {
                    conductors.addAll(userRepo.findAll());
                    for (User conductor : conductors) {
                        if (conductor.getDepartmentByDepartmentId().getName().equals("IT"))
                            conductors.remove(conductor);
                    }
                } else conductors.addAll(userRepo.findByRoleByRoleId_RoleName("Student"));
                break;
            default:
                break;
        }
        return conductors;
    }

    @Override
    public List<User> autoGenerateViewers(FeedbackDetailsModel detailModel) {
        List<User> viewers = new ArrayList<>();
        Course course; List<MajorCourse> majorCourses;
        switch (detailModel.getType().getDescription()) {
            case "Major":
                viewers = userFilteringRepo.findByRoleByRoleId_RoleNameAndMajorByMajorId("HeadOfAcademic", detailModel.getMajor());
                break;
            case "Course":
                course = detailModel.getCourse();
                majorCourses = majorCourseRepo.findByCourseByCourseId(course);
                viewers.addAll(userFilteringRepo.findByRoleByRoleIdAndMajorByMajorId_MajorCoursesByIdContains(
                        roleRepo.findByRoleName("HeadOfAcademic"), majorCourses
                ));
                viewers.addAll(userFilteringRepo.findByRoleByRoleIdAndMajorByMajorId_MajorCoursesByIdContains(
                        roleRepo.findByRoleName("Lecturer"), majorCourses
                ));
                break;
            case "Clazz":
                course = detailModel.getClazz().getCourseByCourseId();
                majorCourses = majorCourseRepo.findByCourseByCourseId(course);
                viewers.addAll(userFilteringRepo.findByRoleByRoleIdAndMajorByMajorId_MajorCoursesByIdContains(
                        roleRepo.findByRoleName("HeadOfAcademic"), majorCourses
                ));
                break;
            case "Department":
                viewers = userFilteringRepo.findByRoleByRoleId_RoleNameAndMajorByMajorId("HeadOfAcademic", detailModel.getMajor());
                break;
        }
        return viewers;
    }

    @Override
    public FeedbackDetailsModel customizeConductors(FeedbackDetailsModel model, int[] conductorIds) {
        try {
            return null;
        } catch (UnexpectedRollbackException e) {
            logger.log(Level.FINE, e.toString());
            return null;
        }
    }

    @Override
    public FeedbackDetailsModel customizeViewers(FeedbackDetailsModel model, int[] viewerIds) {
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
