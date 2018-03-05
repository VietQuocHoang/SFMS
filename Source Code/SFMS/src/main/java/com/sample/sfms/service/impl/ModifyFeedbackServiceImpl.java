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
    private UserFeedbackRepository userFeedbackRepo;

    @Autowired
    private StudentClazzRepository studentClazzRepo;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private UserFilteringRepository userFilteringRepo;

    @Override
    public ResponseEntity<Feedback> getFeedback(int id) {
        Feedback fb = feedbackRepo.findOne(id);
        try {
            return new ResponseEntity<Feedback>(feedbackRepo.findOne(id), HttpStatus.OK);
        } catch (UnexpectedRollbackException e) {
            logger.log(Level.FINE, e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
//        String[] conductorIds = fb.getConductors().split("/"), viewerIds = fb.getViewers().split("/");
//        List<User> conductors = new ArrayList<>(), viewers = new ArrayList<>();
//        User tmp;
//        if (conductorIds != null) {
//            for (String conductorId : conductorIds) {
//                tmp = userRepo.findOne(Integer.parseInt(conductorId));
//                if (tmp != null) conductors.add(tmp);
//            }
//        }
//        if (viewerIds != null) {
//            for (String viewerId : viewerIds) {
//                tmp = userRepo.findOne(Integer.parseInt(viewerId));
//                if (tmp != null) viewers.add(tmp);
//            }
//        }
//        List<FeedbackDetailsModel> details = new ArrayList<>();
//        switch (fb.getTypeByTypeId().getDescription()) {
//            case "Major":
//                details.add(new FeedbackDetailsModel(conductors, viewers, fb.getMajorByMajorId(), null, null, null, fb.getTypeByTypeId()));
//                break;
//            case "Course":
//                details.add(new FeedbackDetailsModel(conductors, viewers, null, fb.getCourseByCourseId(), null, null, fb.getTypeByTypeId()));
//                break;
//            case "Clazz":
//                details.add(new FeedbackDetailsModel(conductors, viewers, null, null, fb.getClazzByClazzId(), null, fb.getTypeByTypeId()));
//                break;
//            case "Department":
//                details.add(new FeedbackDetailsModel(conductors, viewers, null, null, null, fb.getDepartmentByDepartmentId(), fb.getTypeByTypeId()));
//                break;
//            default:
//                break;
//        }
    }

    @Override
    public ResponseEntity<Feedback> createFeedbackFromTemplate(int id) {
        try {
            Feedback template = feedbackRepo.findOne(id);
            return new ResponseEntity<>(feedbackRepo.save(new Feedback(
                    template.getFeedbackDes(),
                    template.getFeedbackName(),
                    template.getFeedbackDes(),
                    template.getFeedbackName(),
                    template.getDepartmentByDepartmentId(),
                    template.getCourseByCourseId(),
                    template.getMajorByMajorId(),
                    template.getClazzByClazzId(),
                    template.getTypeByTypeId(),
                    template.getFeedbackByReferenceId()
            )), HttpStatus.OK);
        } catch (UnexpectedRollbackException e) {
            logger.log(Level.FINE, e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @Override
    public ResponseEntity<Feedback> createEmptyFeedback() {
        try {
            return new ResponseEntity<Feedback>(feedbackRepo.save(new Feedback()), HttpStatus.OK);
        } catch (UnexpectedRollbackException e) {
            logger.log(Level.FINE, e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<List<Feedback>> saveNewFeadbacks(ModifyFeedbackModel model) {
        ResponseEntity<List<Feedback>> response;
        try {
            List<Feedback> affected = new ArrayList<>();
//            Feedback sharedTemplate = model.getFeedback(), tmp;
//            Type t;
//            String selectedusers;
//            for (FeedbackDetailsModel detail : model.getSelectedObjs()) {
//                t = detail.getType();
//                selectedusers = "";
//                tmp = sharedTemplate;
//                switch (t.getDescription()) {
//                    case "Major":
//                        tmp.setMajorByMajorId(majorRepo.findOne(detail.getMajor().getId()));
//                        break;
//                    case "Course":
//                        tmp.setCourseByCourseId(courseRepo.findOne(detail.getCourse().getId()));
//                        break;
//                    case "Clazz":
//                        tmp.setClazzByClazzId(clazzRepo.findOne(detail.getClazz().getId()));
//                        break;
//                    case "Department":
//                        tmp.setDepartmentByDepartmentId(departmentRepo.findOne(detail.getDepartment().getId()));
//                        break;
//                    default:
//                        break;
//                }
//                tmp.setTypeByTypeId(t);
//                for (User conductor : detail.getConductors()) {
//                    selectedusers += conductor.getId() + "/";
//                }
//                tmp.setConductors(selectedusers.substring(0, selectedusers.length() - 1));
//                selectedusers = "";
//                for (User viewer : detail.getReportviewers()) {
//                    selectedusers += viewer.getId() + "/";
//                }
//                tmp.setViewers(selectedusers.substring(0, selectedusers.length() - 1));
//                affected.add(feedbackRepo.save(tmp));
//            }
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
//                model.getFeedback().setConductors("");
//                model.getFeedback().setViewers("");
                Feedback f = new Feedback();
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
    public ResponseEntity<Feedback> addTarget(int typeId, int targetId) {
        Feedback response = new Feedback();
        try {
            Type t = typeRepo.findOne(typeId);
            switch (t.getDescription()) {
                case "Major":
                    response = feedbackRepo.save(new Feedback(null, null, majorRepo.findOne(targetId), null, t));
                    break;
                case "Course":
                    response = feedbackRepo.save(new Feedback(null, courseRepo.findOne(targetId), null, null, t));
                    break;
                case "Clazz":
                    response = feedbackRepo.save(new Feedback(null, null, null, clazzRepo.findOne(targetId), t));
                    break;
                case "Department":
                    response = feedbackRepo.save(new Feedback(departmentRepo.findOne(targetId), null, null, null, t));
                    break;
                default:
                    break;
            }
            autoGenerateConductorsAndViewers(response);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (UnexpectedRollbackException e) {
            logger.log(Level.FINE, e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    public void autoGenerateConductorsAndViewers(Feedback f) {
        List<User> conductors = new ArrayList<>(), viewers = new ArrayList<>();
        List<StudentClazz> studentClazzes = new ArrayList<>();
        List<MajorCourse> majorCourses;
        String specialDepartment = "IT/Y tế/Thư Viện";
        User tmp;
        switch (f.getTypeByTypeId().getDescription()) {
            case "Major":
                conductors = userFilteringRepo.findByMajorByMajorId(f.getMajorByMajorId());
                viewers = userFilteringRepo.findByRoleByRoleId_RoleNameAndMajorByMajorId("HeadOfAcademic", f.getMajorByMajorId());
                break;
            case "Course":
                Course course = f.getCourseByCourseId();
                //set default conductors
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
//                set default viewers
                majorCourses = majorCourseRepo.findByCourseByCourseId(course);
                viewers.addAll(userFilteringRepo.findByRoleByRoleIdAndMajorByMajorId_MajorCoursesByIdContains(
                        roleRepo.findByRoleName("HeadOfAcademic"), majorCourses
                ));
                viewers.addAll(userFilteringRepo.findByRoleByRoleIdAndMajorByMajorId_MajorCoursesByIdContains(
                        roleRepo.findByRoleName("Lecturer"), majorCourses
                ));
                break;
            case "Clazz":
                studentClazzes.addAll(studentClazzRepo.findByClazzByClazzId(f.getClazzByClazzId()));
                for (StudentClazz sc : studentClazzes) {
                    tmp = sc.getUserByUserId();
                    if (!conductors.contains(tmp)) conductors.add(tmp);
                }
                course = f.getClazzByClazzId().getCourseByCourseId();
                majorCourses = majorCourseRepo.findByCourseByCourseId(course);
                viewers.addAll(userFilteringRepo.findByRoleByRoleIdAndMajorByMajorId_MajorCoursesByIdContains(
                        roleRepo.findByRoleName("HeadOfAcademic"), majorCourses
                ));
                break;
            case "Department":
                if (specialDepartment.contains(f.getDepartmentByDepartmentId().getName())) {
                    conductors.addAll(userRepo.findAll());
                    for (User conductor : conductors) {
                        if (conductor.getDepartmentByDepartmentId().getName().equals("IT"))
                            conductors.remove(conductor);
                    }
                } else conductors.addAll(userRepo.findByRoleByRoleId_RoleName("Student"));
                viewers = userFilteringRepo.findByRoleByRoleId_RoleNameAndMajorByMajorId("HeadOfAcademic", f.getMajorByMajorId());
                break;
            default:
                break;
        }
        for (User u : conductors) {
            userFeedbackRepo.save(new UserFeedback(u.getId(), f.getId(), true, false, false));
        }
        UserFeedback uf;
        for (User u : viewers) {
            uf = userFeedbackRepo.findOne(new UserFeedbackPK(u.getId(), f.getId()));
            if (uf != null) {
                uf.setViewer(true);
            } else userFeedbackRepo.save(new UserFeedback(u.getId(), f.getId(), false, true, false));
        }
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


    @Override
    public ResponseEntity<List<User>> loadConductors(int id) {
        List<User> conductors = new ArrayList<>();
        try {
            for (UserFeedback uf : feedbackRepo.findOne(id).getUserFeedbacksById()) {
                conductors.add(uf.getUserByUserId());
            }
            return new ResponseEntity<>(conductors, HttpStatus.OK);
        } catch (UnexpectedRollbackException e) {
            logger.log(Level.FINE, e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<List<User>> loadViewers(int id) {
        List<User> viewers = new ArrayList<>();
        try {
            for (UserFeedback uf : feedbackRepo.findOne(id).getUserFeedbacksById()) {
                viewers.add(uf.getUserByUserId());
            }
            return new ResponseEntity<>(viewers, HttpStatus.OK);
        } catch (UnexpectedRollbackException e) {
            logger.log(Level.FINE, e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<List<User>> loadTargets(int[] ids) {

//        try {
//            for (int i: ids) {
//
//            }
//            return new ResponseEntity<>(viewers, HttpStatus.OK);
//        } catch (UnexpectedRollbackException e) {
//            logger.log(Level.FINE, e.toString());
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
        return null;
    }


}
