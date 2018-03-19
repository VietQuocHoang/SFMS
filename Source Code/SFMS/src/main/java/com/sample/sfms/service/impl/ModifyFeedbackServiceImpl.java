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
import java.util.Set;
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

//    @Autowired
//    private MajorCourseRepository majorCourseRepo;

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

    @Autowired
    private AnswerRepository answerRepo;

    @Override
    public ResponseEntity getFeedback(int id) {
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
            feedbackRepo.delete(template);
            return new ResponseEntity<>(affected, HttpStatus.OK);
        } catch (UnexpectedRollbackException e) {
            logger.log(Level.FINE, e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Feedback> saveTemplateFeadback(int feedbackId, List<Integer> targetIds) {
        try {
            List<Feedback> affected = new ArrayList<>();
            Feedback target = new Feedback(), template = feedbackRepo.findOne(feedbackId);
            if (template == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            for (int targetId : targetIds) {
                target = feedbackRepo.findOne(targetId);
                if (target != null) {
                    feedbackRepo.delete(target);
                }
            }
            template.setIsTemplate(true);
            template.setIsPublished(false);
            template = feedbackRepo.save(template);
            return new ResponseEntity<>(template, HttpStatus.OK);
        } catch (UnexpectedRollbackException e) {
            logger.log(Level.FINE, e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Feedback> updateSelectedTemplate(int feedbackId, List<Integer> targetIds) {
        try {
            Feedback feedback = feedbackRepo.findOne(feedbackId), target = new Feedback();
            feedback.setId(feedback.getFeedbackByReferenceId().getId());
            feedback.setFeedbackByReferenceId(null);
            for (int id : targetIds) {
                target = feedbackRepo.findOne(id);
                if (target != null) {
                    feedbackRepo.delete(target);
                }
            }
            Feedback template = feedback.getFeedbackByReferenceId();
            for (Question q : template.getQuestionsById()) {
                questionRepo.delete(q);
            }
            template = clone(feedback, template);
            template.setStartDate(null);
            template.setEndDate(null);
            template.setIsTemplate(true);
            template.setIsPublished(false);
            template.setSemesterBySemesterId(null);
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
            return new ResponseEntity<Feedback>(feedbackRepo.save(new Feedback(description, title, typeRepo.findByDescription("Lớp"))), HttpStatus.OK);
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
    public ResponseEntity cancelProcess(int id, List<Integer> targetIds) {
        try {
            if (feedbackRepo.exists(id)) feedbackRepo.delete(id);
            for (int targetId : targetIds) {
                if (feedbackRepo.exists(targetId)) feedbackRepo.delete(targetId);
            }
            return new ResponseEntity(HttpStatus.OK);
        } catch (UnexpectedRollbackException e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }


    @Override
    public ResponseEntity<List<Integer>> addTarget(int feedbackId, int targetId, List<Integer> targetIds) {
        Feedback response = new Feedback();
        try {
            Type t = feedbackRepo.findOne(feedbackId).getTypeByTypeId();
            switch (t.getDescription()) {
                case "Chuyên ngành":
                    for (int id : targetIds) {
                        response = feedbackRepo.findOne(id);
                        if (response.getMajorByMajorId().getId() == targetId)
                            return new ResponseEntity<>(targetIds, HttpStatus.ALREADY_REPORTED);
                    }
                    response = feedbackRepo.save(new Feedback(null, null, majorRepo.findOne(targetId), null, t));
                    targetIds.add(response.getId());
                    break;
                case "Môn học":
                    for (int id : targetIds) {
                        response = feedbackRepo.findOne(id);
                        if (response.getCourseByCourseId().getId() == targetId)
                            return new ResponseEntity<>(targetIds, HttpStatus.ALREADY_REPORTED);
                    }
                    response = feedbackRepo.save(new Feedback(null, courseRepo.findOne(targetId), null, null, t));
                    targetIds.add(response.getId());
                    break;
                case "Lớp":
                    for (int id : targetIds) {
                        response = feedbackRepo.findOne(id);
                        if (response.getClazzByClazzId().getId() == targetId)
                            return new ResponseEntity<>(targetIds, HttpStatus.ALREADY_REPORTED);
                    }
                    response = feedbackRepo.save(new Feedback(null, null, null, clazzRepo.findOne(targetId), t));
                    targetIds.add(response.getId());
                    break;
                case "Phòng ban":
                    for (int id : targetIds) {
                        response = feedbackRepo.findOne(id);
                        if (response.getDepartmentByDepartmentId().getId() == targetId)
                            return new ResponseEntity<>(targetIds, HttpStatus.ALREADY_REPORTED);
                    }
                    response = feedbackRepo.save(new Feedback(departmentRepo.findOne(targetId), null, null, null, t));
                    targetIds.add(response.getId());
                    break;
                default:
                    break;
            }
            autoGenerateConductorsAndViewers(response);
            return new ResponseEntity<>(targetIds, HttpStatus.OK);
        } catch (UnexpectedRollbackException e) {
            logger.log(Level.FINE, e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<List<Integer>> removeTarget(int id, List<Integer> targetIds) {
        Feedback response = new Feedback();
        int removedId = 0;
        try {
            for (int targetId : targetIds) {
                response = feedbackRepo.findOne(targetId);
                Type t = response.getTypeByTypeId();
                switch (t.getDescription()) {
                    case "Chuyên ngành":
                        if (response.getMajorByMajorId().getId() == id)
                            userFeedbackRepo.delete(response.getUserFeedbacksById());
                        feedbackRepo.delete(response);
                        removedId = response.getId();
                        break;
                    case "Môn học":
                        if (response.getCourseByCourseId().getId() == id)
                            userFeedbackRepo.delete(response.getUserFeedbacksById());
                        feedbackRepo.delete(response);
                        removedId = response.getId();
                        break;
                    case "Lớp":
                        if (response.getClazzByClazzId().getId() == id) {
                            userFeedbackRepo.delete(response.getUserFeedbacksById());
//                            response.setUserFeedbacksById(null);
                            feedbackRepo.delete(response.getFeedbacksById());
//                            response.setFeedbacksById(null);
                            for (Question q : response.getQuestionsById()) {
                                for (Optionn opt : q.getOptionsById()) {
                                    answerRepo.delete(opt.getAnswersById());
                                }
                                optionRepo.delete(q.getOptionsById());
                            }
                            questionRepo.delete(response.getQuestionsById());
//                            response.setQuestionsById(null);
                            removedId = response.getId();
                            feedbackRepo.delete(response);
                        }
                        break;
                    case "Phòng ban":
                        if (response.getDepartmentByDepartmentId().getId() == id)
                            userFeedbackRepo.delete(response.getUserFeedbacksById());
                        feedbackRepo.delete(response);
                        removedId = response.getId();
                        break;
                    default:
                        return new ResponseEntity<>(targetIds, HttpStatus.BAD_REQUEST);
                }
            }
            if (removedId == 0) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            targetIds.remove((Object) removedId);
            return new ResponseEntity<>(targetIds, HttpStatus.OK);
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
//        List<MajorCourse> majorCourses;
        String specialDepartment = "IT/Y tế/Thư Viện";
        User tmp;
        switch (f.getTypeByTypeId().getDescription()) {
            case "Chuyên ngành":
                conductors = userFilteringRepo.findByMajorByMajorId(f.getMajorByMajorId());
                viewers = userFilteringRepo.findByRoleByRoleIdAndMajorByMajorId(roleRepo.findByRoleName("Trưởng phòng"), f.getMajorByMajorId());
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
                viewers.addAll(userFilteringRepo.findByRoleByRoleIdAndMajorByMajorId_CoursesByIdContains(
                        roleRepo.findByRoleName("Trưởng phòng"), course
                ));
                viewers.addAll(userFilteringRepo.findByRoleByRoleIdAndMajorByMajorId_CoursesByIdContains(
                        roleRepo.findByRoleName("Giảng viên"), course
                ));
                break;
            case "Lớp":
                studentClazzes.addAll(studentClazzRepo.findByClazzByClazzId(f.getClazzByClazzId()));
                for (StudentClazz sc : studentClazzes) {
                    tmp = sc.getUserByUserId();
                    if (!conductors.contains(tmp)) conductors.add(tmp);
                }
                course = f.getClazzByClazzId().getCourseByCourseId();
                viewers.addAll(userFilteringRepo.findByRoleByRoleIdAndMajorByMajorId_CoursesByIdContains(
                        roleRepo.findByRoleName("Trưởng phòng"), course
                ));
                break;
            case "Phòng ban":
                if (specialDepartment.contains(f.getDepartmentByDepartmentId().getName())) {
                    conductors.addAll(userRepo.findAll());
                    for (User conductor : conductors) {
                        if (conductor.getDepartmentByDepartmentId().getName().equals("IT"))
                            conductors.remove(conductor);
                    }
                } else conductors.addAll(userRepo.findByRoleByRoleId_RoleName("Học sinh"));
                viewers = userFilteringRepo.findByRoleByRoleIdAndMajorByMajorId(roleRepo.findByRoleName("Trưởng phòng"), f.getMajorByMajorId());
                break;
            default:
                break;
        }
        UserFeedback uf;
        for (User u : conductors) {
            uf = userFeedbackRepo.save(new UserFeedback(u.getId(), f.getId(), true, false, false));
//            uf.setConductor(true);
//            userFeedbackRepo.save(uf);
        }
        for (User u : viewers) {
            uf = userFeedbackRepo.findOne(new UserFeedbackPK(u.getId(), f.getId()));
            if (uf != null) {
                uf.setViewer(true);
                userFeedbackRepo.save(uf);
            } else {
                uf = userFeedbackRepo.save(new UserFeedback(u, f, false, true, false));
            }
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
            User u = userRepo.findOne(userId);
            Feedback f = feedbackRepo.findOne(targetId);
//            UserFeedback uf = userFeedbackRepo.save(new UserFeedback(u.getId(), f.getId(), true, false, false));
//            uf.setConductor(true);
//            return new ResponseEntity<>(userFeedbackRepo.save(uf), HttpStatus.OK);
            return new ResponseEntity<>(userFeedbackRepo.save(new UserFeedback(u.getId(), f.getId(), true, false, false)), HttpStatus.OK);
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
            User u = userRepo.findOne(userId);
            Feedback f = feedbackRepo.findOne(targetId);
//            UserFeedback uf = userFeedbackRepo.save(new UserFeedback(u.getId(), f.getId(), false, true, false));
//            uf.setViewer(true);
            return new ResponseEntity<>(userFeedbackRepo.save(new UserFeedback(u.getId(), f.getId(), false, true, false)), HttpStatus.OK);
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
            if (feedback.getEndDate() != null) {
                if (feedback.getEndDate().before(start)) feedback.setEndDate(null);
            }
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
    public ResponseEntity<List<Major>> filterMajors(String majorKey) {
        try {
            return new ResponseEntity<>(majorRepo.findAll(), HttpStatus.OK);
        } catch (UnexpectedRollbackException e) {
            logger.log(Level.FINE, e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<List<Course>> filterCourses(String name) {
        try {
            return new ResponseEntity<>(courseRepo.findByNameContains(name), HttpStatus.OK);
        } catch (UnexpectedRollbackException e) {
            logger.log(Level.FINE, e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<List<Department>> filterDepartments() {
        try {
            return new ResponseEntity<>(departmentRepo.findAll(), HttpStatus.OK);
        } catch (UnexpectedRollbackException e) {
            logger.log(Level.FINE, e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<List<User>> filterLecturers(String majorKey, String nameKey) {
        try {
//            return userRepo.findByRoleByRoleId_RoleNameContainsAndFullnameContainsAndMajorByMajorId_NameContains("Giảng viên", nameKey, majorKey);
//                return userRepo.findByRoleByRoleId_RoleName("Giảng viên");
            return new ResponseEntity<>(userRepo.findAll(), HttpStatus.OK);
        } catch (UnexpectedRollbackException e) {
            logger.log(Level.FINE, e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<List<Clazz>> filterClazz(String majorKey, String courseKey, String semesterKey, String lecturerKey) {
        try {
//            return new ResponseEntity<>(clazzRepo.filteringver2(majorKey, courseKey, semesterKey, lecturerKey
////                    , "Giảng viên, Trưởng ban, Trưởng phòng"
//            ), HttpStatus.OK);
            List<Major> majors = majorRepo.findByNameContainsOrCodeContains(majorKey, majorKey);
            List<Course> courses = courseRepo.findByNameContainsOrCodeContains(courseKey, courseKey);
//            List<Course> courses = courseRepo.findByNameContainsOrCodeContainsAndMajorByMajorId_NameContainsOrMajorByMajorId_CodeContains(courseKey, courseKey, majorKey, majorKey);
            List<Semester> semesters = semesterRepo.findByTitleContains(semesterKey);
            List<User> lecturers = userFilteringRepo.findByFullnameContainsOrCodeContains(lecturerKey, lecturerKey);
            List<Clazz> clazzes = clazzRepo.findAll();
            for (Course c : courses) {
                if (!majors.contains(c.getMajorByMajorId())) courses.remove(c);
            }
            for (User u : lecturers) {
                if (!u.getRoleByRoleId().getRoleName().equals("Giảng viên") &&
                        !u.getRoleByRoleId().getRoleName().equals("Trưởng ban") &&
                        !u.getRoleByRoleId().getRoleName().equals("Trưởng phòng") &&
                        !u.getRoleByRoleId().getRoleName().equals("Tổ trưởng") &&
                        !majors.contains(u.getMajorByMajorId())) lecturers.remove(u);
            }
            for (Clazz c : clazzRepo.findAll()) {
                if (!courses.contains(c.getCourseByCourseId()) ||
                        !semesters.contains(c.getSemesterBySemesterId()) ||
                        !lecturers.contains(c.getUserByLecturerId())) clazzes.remove(c);
            }
            return new ResponseEntity<>(clazzes, HttpStatus.OK);
//            List<Clazz> clazzes = clazzRepo.filteringver2(majorKey, courseKey, semesterKey, lecturerKey);
//            return new ResponseEntity<>(clazzes, HttpStatus.OK);
        } catch (UnexpectedRollbackException e) {
            logger.log(Level.FINE, e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


    }

    @Override
    public ResponseEntity<List<Clazz>> filterClazz(int majorKey, int courseKey, int semesterkey, int lecturerKey) {
        try {
            Major major = null;
            Semester semester = null;
            User lecturer = null;
            List<Course> courses = new ArrayList<>();
            if (courseKey == 0) {
                if (majorKey != 0) courses = (List<Course>) majorRepo.findOne(majorKey).getCoursesById();
            } else courses.add(courseRepo.findOne(courseKey));
            if (semesterkey != 0) semester = semesterRepo.findOne(semesterkey);
            if (lecturerKey != 0) lecturer = userRepo.findOne(lecturerKey);
            List<Clazz> clazzes = clazzRepo.findAll();
            for (Clazz c : clazzRepo.findAll()) {
                if (!courses.isEmpty()) {
                    if (!courses.contains(c.getCourseByCourseId())) clazzes.remove(c);
                }
                if (semester != null) {
                    if (c.getSemesterBySemesterId() != semester) clazzes.remove(c);
                }
                if (lecturer != null) {
                    if (c.getUserByLecturerId() != lecturer) clazzes.remove(c);
                }
            }
            return new ResponseEntity<>(clazzes, HttpStatus.OK);
//            List<Clazz> clazzes = clazzRepo.filteringver2(majorKey, courseKey, semesterKey, lecturerKey);
//            return new ResponseEntity<>(clazzes, HttpStatus.OK);
        } catch (UnexpectedRollbackException e) {
            logger.log(Level.FINE, e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public List filterSemester(String title) {
        return semesterRepo.findByTitleContains(title);
    }

    @Override
    public ResponseEntity<List<Department>> loadDepartmentTargets(List<Integer> ids) {
        try {
            List<Department> results = new ArrayList<>();
            Department d;
            for (int id : ids) {
                d = feedbackRepo.findOne(id).getDepartmentByDepartmentId();
                if (d != null) results.add(d);
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
            Major m;
            for (int id : ids) {
                m = feedbackRepo.findOne(id).getMajorByMajorId();
                if (m != null) results.add(m);
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
            Course c;
            for (int id : ids) {
                c = feedbackRepo.findOne(id).getCourseByCourseId();
                if (c != null) results.add(c);
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
            Clazz c;
            for (int id : ids) {
                c = feedbackRepo.findOne(id).getClazzByClazzId();
                if (c != null) results.add(c);
            }
            return new ResponseEntity<>(results, HttpStatus.OK);
        } catch (UnexpectedRollbackException e) {
            logger.log(Level.FINE, e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
