package com.sample.sfms.service.impl;

import com.sample.sfms.entity.*;
import com.sample.sfms.repository.*;
import com.sample.sfms.service.interf.ModifyFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.UnexpectedRollbackException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    //    Repositories
    @Autowired
    private FeedbackRepository feedbackRepo;

    @Autowired
    private MajorRepository majorRepo;

    @Autowired
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

    @Autowired
    private QuestionRepository questionRepo;

    @Autowired
    private OptionnRepository optionRepo;

    @Autowired
    private SemesterRepository semesterRepo;

    @Override
    public ResponseEntity<Feedback> getFeedback(int id) {
        Feedback fb = feedbackRepo.findOne(id);
        try {
            return new ResponseEntity<Feedback>(feedbackRepo.findOne(id), HttpStatus.OK);
        } catch (UnexpectedRollbackException e) {
            logger.log(Level.FINE, e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<List<Feedback>> savePublishFeadbacks(int feedbackId, List<Integer> targetIds) {
        try {
            List<Feedback> affected = new ArrayList<>();
            Feedback target = new Feedback(), template = feedbackRepo.findOne(feedbackId);
            if (template == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            for (int targetId : targetIds) {
                target = feedbackRepo.findOne(targetId);
                if (target != null) {
                    target = clone(template, target);
                    target.setIsPublished(true);
                    target.setIsTemplate(false);
                    affected.add(feedbackRepo.save(target));
                }
            }
            feedbackRepo.delete(feedbackId);
            return new ResponseEntity<>(affected, HttpStatus.OK);
        } catch (UnexpectedRollbackException e) {
            logger.log(Level.FINE, e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<List<Feedback>> saveTemplateFeadbacks(int feedbackId, List<Integer> targetIds) {
        try {
            List<Feedback> affected = new ArrayList<>();
            Feedback target = new Feedback(), template = feedbackRepo.findOne(feedbackId);
            if (template == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            for (int targetId : targetIds) {
                target = feedbackRepo.findOne(targetId);
                if (target != null) {
                    target = clone(template, target);
                    target.setIsPublished(false);
                    target.setIsTemplate(true);
                    affected.add(feedbackRepo.save(target));
                }
            }
            feedbackRepo.delete(feedbackId);
            return new ResponseEntity<>(affected, HttpStatus.OK);
        } catch (UnexpectedRollbackException e) {
            logger.log(Level.FINE, e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Feedback> updateSelectedTemplate(int feedbackId, List<Integer> targetIds) {
        try {
            Feedback feedback = feedbackRepo.findOne(feedbackId);
            feedback.setId(feedback.getFeedbackByReferenceId().getId());
            feedback.setFeedbackByReferenceId(null);
            for (int id : targetIds) {
                feedbackRepo.delete(id);
            }
            return new ResponseEntity<>(feedbackRepo.save(feedback), HttpStatus.OK);
        } catch (UnexpectedRollbackException e) {
            logger.log(Level.FINE, e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public Feedback clone(Feedback template, Feedback target) {
        target.setTypeByTypeId(template.getTypeByTypeId());
        target.setFeedbackDes(template.getFeedbackDes());
        target.setFeedbackName(template.getFeedbackName());
        target.setFeedbackByReferenceId(template.getFeedbackByReferenceId());
        target.setCreateDate(template.getCreateDate());
        target.setStartDate(template.getStartDate());
        target.setEndDate(template.getEndDate());
        target.setIsTemplate(template.getIsTemplate());
        target.setSemesterBySemesterId(template.getSemesterBySemesterId());
        Question question = new Question();
        Optionn optionn = new Optionn();
        for (Question q : template.getQuestionsById()) {
            question = new Question(q.getType(), q.getSuggestion(), q.getIsRequied(), q.getQuestionContent(),
                    q.getCriteriaByCriteriaId(), target);
            question = questionRepo.save(question);
            for (Optionn o : q.getOptionsById()) {
                optionn = new Optionn(o.getOptionnContent(), o.getPoint(), o.getQuestionByQuestionId());

            }
        }
        return target;
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
                    template//selected template
            )), HttpStatus.OK);
        } catch (UnexpectedRollbackException e) {
            logger.log(Level.FINE, e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Feedback> createEmptyFeedback(String title, String description) {
        try {
            return new ResponseEntity<Feedback>(feedbackRepo.save(new Feedback(description, title)), HttpStatus.OK);
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
    public ResponseEntity<Feedback> addTarget(int feedbackId, int targetId, List<Integer> targetIds) {
        Feedback response = new Feedback();
        try {
            Type t = feedbackRepo.findOne(feedbackId).getTypeByTypeId();
            switch (t.getDescription()) {
                case "Chuyên ngành":
                    for (int id : targetIds) {
                        response = feedbackRepo.findOne(id);
                        if (response.getMajorByMajorId().getId() == targetId)
                            return new ResponseEntity<>(response, HttpStatus.ALREADY_REPORTED);
                    }
                    response = feedbackRepo.save(new Feedback(null, null, majorRepo.findOne(targetId), null, t));
                    break;
                case "Môn học":
                    for (int id : targetIds) {
                        response = feedbackRepo.findOne(id);
                        if (response.getCourseByCourseId().getId() == targetId)
                            return new ResponseEntity<>(response, HttpStatus.ALREADY_REPORTED);
                    }
                    response = feedbackRepo.save(new Feedback(null, courseRepo.findOne(targetId), null, null, t));
                    break;
                case "Lớp":
                    for (int id : targetIds) {
                        response = feedbackRepo.findOne(id);
                        if (response.getClazzByClazzId().getId() == targetId)
                            return new ResponseEntity<>(response, HttpStatus.ALREADY_REPORTED);
                    }
                    response = feedbackRepo.save(new Feedback(null, null, null, clazzRepo.findOne(targetId), t));
                    break;
                case "Phòng ban":
                    for (int id : targetIds) {
                        response = feedbackRepo.findOne(id);
                        if (response.getDepartmentByDepartmentId().getId() == targetId)
                            return new ResponseEntity<>(response, HttpStatus.ALREADY_REPORTED);
                    }
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

    @Override
    public ResponseEntity<Feedback> removeTarget(int id, List<Integer> targetIds) {
        Feedback response = new Feedback();
        try {
            for (int targetId : targetIds) {
                response = feedbackRepo.findOne(targetId);
                Type t = response.getTypeByTypeId();
                switch (t.getDescription()) {
                    case "Chuyên ngành":
                        if (response.getMajorByMajorId().getId() == id)
                            feedbackRepo.delete(response);
                        break;
                    case "Môn học":
                        if (response.getCourseByCourseId().getId() == id)
                            feedbackRepo.delete(response);
                        break;
                    case "Lớp":
                        if (response.getClazzByClazzId().getId() == id)
                            feedbackRepo.delete(response);
                        break;
                    case "Phòng ban":
                        if (response.getDepartmentByDepartmentId().getId() == id)
                            feedbackRepo.delete(response);
                        break;
                    default:
                        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (UnexpectedRollbackException e) {
            logger.log(Level.FINE, e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<List<Feedback>> deleteFeedbacks(List<Integer> ids) {
        try {
            Feedback target;
            List<Feedback> affected = new ArrayList<>();
            if (!ids.isEmpty()) {
                for (int id : ids) {
                    target = feedbackRepo.findOne(id);
                    if (target != null) {
                        feedbackRepo.delete(id);
                        affected.add(target);
                    }
                }
            }
            return new ResponseEntity<>(affected, HttpStatus.OK);
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
            case "Chuyên ngành":
                conductors = userFilteringRepo.findByMajorByMajorId(f.getMajorByMajorId());
                viewers = userFilteringRepo.findByRoleByRoleId_RoleNameAndMajorByMajorId("HeadOfAcademic", f.getMajorByMajorId());
                break;
            case "Môn học":
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
            case "Lớp":
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
            case "Phòng ban":
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
    public ResponseEntity<UserFeedback> addConductor(int targetId, int userId) {
        try {
            UserFeedback selected = userFeedbackRepo.findOne(new UserFeedbackPK(userId, targetId));
            if (selected != null) {
                selected.setConductor(true);
                return new ResponseEntity<>(userFeedbackRepo.save(selected), HttpStatus.OK);
            }
            return new ResponseEntity<>(
                    userFeedbackRepo.save(new UserFeedback(
                            feedbackRepo.findOne(targetId).getId()
                            , userRepo.findOne(userId).getId(),
                            true, false, false)), HttpStatus.OK);
        } catch (UnexpectedRollbackException e) {
            logger.log(Level.FINE, e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<UserFeedback> removeConductor(int targetId, int userId) {
        try {
            UserFeedback selected = userFeedbackRepo.findOne(new UserFeedbackPK(userId, targetId));
            if (selected.isViewer()) {
                selected.setConductor(false);
                return new ResponseEntity<>(userFeedbackRepo.save(selected), HttpStatus.OK);
            }
            userFeedbackRepo.delete(selected);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (UnexpectedRollbackException e) {
            logger.log(Level.FINE, e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<UserFeedback> addViewer(int targetId, int userId) {
        try {
            UserFeedback selected = userFeedbackRepo.findOne(new UserFeedbackPK(userId, targetId));
            if (selected != null) {
                selected.setViewer(true);
                userFeedbackRepo.save(selected);
            }
            return new ResponseEntity<>(
                    userFeedbackRepo.save(new UserFeedback(
                            feedbackRepo.findOne(targetId).getId()
                            , userRepo.findOne(userId).getId(),
                            false, true, false)), HttpStatus.OK);
        } catch (UnexpectedRollbackException e) {
            logger.log(Level.FINE, e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<UserFeedback> removeViewer(int targetId, int userId) {
        try {
            UserFeedback selected = userFeedbackRepo.findOne(new UserFeedbackPK(userId, targetId));
            if (selected.isViewer()) {
                selected.setViewer(false);
                return new ResponseEntity<>(userFeedbackRepo.save(selected), HttpStatus.OK);
            }
            userFeedbackRepo.delete(selected);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (UnexpectedRollbackException e) {
            logger.log(Level.FINE, e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity saveFeedback(Feedback feedback) {
        try {
            feedbackRepo.save(feedback);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (UnexpectedRollbackException e) {
            logger.log(Level.FINE, e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Semester> updateSemester(int semesterId, int feedbackId) {
        try {
            Feedback feedback = feedbackRepo.findOne(feedbackId);
            feedback.setSemesterBySemesterId(semesterRepo.findOne(semesterId));
            feedback.setStartDate(null);
            feedback.setEndDate(null);
            return new ResponseEntity<>(feedbackRepo.save(feedback).getSemesterBySemesterId(), HttpStatus.OK);
        } catch (UnexpectedRollbackException e) {
            logger.log(Level.FINE, e.toString());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Feedback> updateType(int typeId, int feedbackId) {
        try {
            Feedback feedback = feedbackRepo.findOne(feedbackId);
            feedback.setTypeByTypeId(typeRepo.findOne(typeId));
            return new ResponseEntity<>(feedbackRepo.save(feedback), HttpStatus.OK);
        } catch (UnexpectedRollbackException e) {
            logger.log(Level.FINE, e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Feedback> setStart(Date start, int feedbackId) {
        try {
            Feedback feedback = feedbackRepo.findOne(feedbackId);
            feedback.setStartDate(start);
            if(feedback.getEndDate()!=null){
                if(feedback.getEndDate().before(start))feedback.setEndDate(null);
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return new ResponseEntity<>(feedbackRepo.save(feedback), HttpStatus.OK);
        } catch (UnexpectedRollbackException e) {
            logger.log(Level.FINE, e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Feedback> setEnd(Date end, int feedbackId) {
        try {
            Feedback feedback = feedbackRepo.findOne(feedbackId);
            feedback.setEndDate(end);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return new ResponseEntity<>(feedbackRepo.save(feedback), HttpStatus.OK);
        } catch (UnexpectedRollbackException e) {
            logger.log(Level.FINE, e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
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
    public ResponseEntity<List<Feedback>> loadTargets(List<Integer> ids) {
        try {
            List<Feedback> results = new ArrayList<>();
            for (int id : ids) {
                results.add(feedbackRepo.findOne(id));
            }
            return new ResponseEntity<>(results, HttpStatus.OK);
        } catch (UnexpectedRollbackException e) {
            logger.log(Level.FINE, e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<List<Type>> loadAllTypes() {
        try {
            return new ResponseEntity<>(typeRepo.findAll(), HttpStatus.OK);
        } catch (UnexpectedRollbackException e) {
            logger.log(Level.FINE, e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<List<Semester>> loadAllSemesters() {
        try {
            return new ResponseEntity<>(semesterRepo.findAll(), HttpStatus.OK);
        } catch (UnexpectedRollbackException e) {
            logger.log(Level.FINE, e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<List<Department>> loadDepartmentTargets(List<Integer> ids) {
        try {
            List<Department> results = new ArrayList<>();
            for (int id : ids) {
                results.add(feedbackRepo.findOne(id).getDepartmentByDepartmentId());
            }
            return new ResponseEntity<>(results, HttpStatus.OK);
        } catch (UnexpectedRollbackException e) {
            logger.log(Level.FINE, e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<List<Major>> loadMajorTargets(List<Integer> ids) {
        try {
            List<Major> results = new ArrayList<>();
            for (int id : ids) {
                results.add(feedbackRepo.findOne(id).getMajorByMajorId());
            }
            return new ResponseEntity<>(results, HttpStatus.OK);
        } catch (UnexpectedRollbackException e) {
            logger.log(Level.FINE, e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<List<Course>> loadCourseTargets(List<Integer> ids) {
        try {
            List<Course> results = new ArrayList<>();
            for (int id : ids) {
                results.add(feedbackRepo.findOne(id).getCourseByCourseId());
            }
            return new ResponseEntity<>(results, HttpStatus.OK);
        } catch (UnexpectedRollbackException e) {
            logger.log(Level.FINE, e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<List<Clazz>> loadClazzTargets(List<Integer> ids) {
        try {
            List<Clazz> results = new ArrayList<>();
            for (int id : ids) {
                results.add(feedbackRepo.findOne(id).getClazzByClazzId());
            }
            return new ResponseEntity<>(results, HttpStatus.OK);
        } catch (UnexpectedRollbackException e) {
            logger.log(Level.FINE, e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}
