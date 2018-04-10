package com.sample.sfms.service.impl;

import com.sample.sfms.define.QuestionType;
import com.sample.sfms.entity.*;
import com.sample.sfms.model.feedback.FeedbackTargetWrapper;
import com.sample.sfms.model.report.reportList.ReportLecturerCourse;
import com.sample.sfms.model.report.reportSemester.*;
import com.sample.sfms.repository.*;
import com.sample.sfms.service.interf.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Binh Nguyen on 25-Feb-18.
 */
@Service("reportService")
public class ReportServiceImpl implements ReportService {

    private static final String DEPARTMENT = "Phòng ban";
    private static final String COURSE = "Môn học";
    private static final String CLASS = "Lớp";
    private static final String MAJOR = "Chuyên ngành";


    @Autowired
    private DepartmentRepository depRepo;

    @Autowired
    private MajorRepository majorRepo;

    @Autowired
    private CourseRepository courseRepo;

    @Autowired
    private ClazzRepository clazzRepository;

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private CriteriaRepository criteriaRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private TypeRepository typeRepository;

    @Autowired
    private SemesterRepository semesterRepository;

    @Override
    public List<Object> loadListReport(String type) {
        List<Object> results = new ArrayList<>();
        switch (type) {
            case "major":
                results.addAll(majorRepo.filtering());
                break;
            case "course":
                results.addAll(courseRepo.filtering());
                break;
            case "department":
                results.addAll(depRepo.filtering());
                break;
            default:
                break;
        }
        return results;
    }

    @Override
    public FeedbackTargetWrapper loadListFeedbackTargetWrapper() {
        FeedbackTargetWrapper feedbackTargetWrapper = new FeedbackTargetWrapper();
        List<Object[]> objectList = clazzRepository.findAllCourseCorrespondingToEachLecturerTaught();
        ReportLecturerCourse lecturerCourse;
        for (Object[] o : objectList) {
            lecturerCourse = new ReportLecturerCourse();
            lecturerCourse.mapFromObject(o);
            feedbackTargetWrapper.getLecturerCourseLists().add(lecturerCourse);
        }
        feedbackTargetWrapper.setCourses(courseRepo.findAll());
        feedbackTargetWrapper.setDepartments(depRepo.findAll());
        feedbackTargetWrapper.setMajors(majorRepo.findAll());
        return feedbackTargetWrapper;
    }

    @Override
    public ReportSemesterModel getReportSemesterDetail(int typeId, int semId, int targetId) {
        if (typeId <= 0 || targetId <= 0 || semId <= 0) {
            return null;
        } else {
            List<Feedback> feedbackList;
            Type type = typeRepository.findOne(typeId);
            if (type == null) {
                return null;
            }
            ReportSemesterModel reportSemesterModel = new ReportSemesterModel();
            reportSemesterModel.setType(type.getDescription());
            Semester semester = semesterRepository.findOne(semId);
            reportSemesterModel.setSemester(semester.getTitle());
            String target = type.getDescription();
            switch (target) {
                case MAJOR: {
                    feedbackList = feedbackRepository.getListFeedbackBySemIdAndMajorId(semId, targetId);
                    Major major = majorRepo.findOne(targetId);
                    reportSemesterModel.setTarget(major.getName());
                    break;
                }
                case COURSE: {
                    feedbackList = feedbackRepository.getListFeedbackBySemIdAndCourseId(semId, targetId);
                    Course course = courseRepo.findOne(targetId);
                    reportSemesterModel.setTarget(course.getName());
                    break;
                }
                case CLASS: {
                    feedbackList = feedbackRepository.getListFeedbackBySemIdAndClassId(semId, targetId);
                    Clazz clazz = clazzRepository.findOne(targetId);
                    reportSemesterModel.setTarget(clazz.getClassName());
                    break;
                }
                case DEPARTMENT: {
                    feedbackList = feedbackRepository.getListFeedbackBySemIdAndDepId(semId, targetId);
                    Department department = depRepo.findOne(targetId);
                    reportSemesterModel.setTarget(department.getName());
                    break;
                }
                default: {
                    return null;
                }
            }
            if (null == feedbackList) {
                return null;
            }
            List<Criteria> criteriaList = criteriaRepository.findAll();
            List<CriteriaReportModel> criteriaReportModels = new ArrayList<>();
            CriteriaReportModel criteriaReportModel;
            for (Criteria c : criteriaList) {
                criteriaReportModel = new CriteriaReportModel();
                criteriaReportModel.setCriteriaName(c.getCriteria());
                criteriaReportModel.setCriteriaId(c.getId());
                criteriaReportModels.add(criteriaReportModel);
            }
            for (Feedback f : feedbackList) {
                List<Question> questionList = new ArrayList<>(f.getQuestionsById());
                if (!questionList.isEmpty()) {
                    for (Question q : questionList) {
                        String questionType = q.getType();
                        double questionAvgPoint = 0;
                        switch (questionType) {
                            case QuestionType.CHECKBOX:
                            case QuestionType.RADIO:
                            case QuestionType.STAR: {
                                YNQuestionReportModel ynQuestionReportModel = new YNQuestionReportModel();
                                ynQuestionReportModel.setQuestionContent(q.getQuestionContent());
                                ynQuestionReportModel.setFeedbackId(f.getId());
                                int numberOfQuestionResponse = answerRepository.countNumberOfQuestionsResponse(q.getId());
                                ynQuestionReportModel.setResponseCount(numberOfQuestionResponse);
                                ynQuestionReportModel.setFeedbackId(f.getId());
                                ynQuestionReportModel.setFeedbackName(f.getFeedbackName());
                                ynQuestionReportModel.setSuggestion(q.getSuggestion());
                                List<Optionn> optionnList = new ArrayList<>(q.getOptionsById());
                                if (!optionnList.isEmpty()) {
                                    double totalPoint = 0;
                                    double totalSelected = 0;
                                    for (Optionn o : optionnList) {
                                        YNAnswerReportModel ynAnswerReportModel = new YNAnswerReportModel();
                                        ynAnswerReportModel.setOptionPoint(o.getPoint());
                                        ynAnswerReportModel.setOptionContent(o.getOptionnContent());
                                        int selectedCount = answerRepository.countNumberOfTimeAnswerSelected(o.getId());
                                        ynAnswerReportModel.setSelectedCount(selectedCount);
                                        if (ynAnswerReportModel.getOptionPoint() == 0) {
                                            List<Answer> answerList = answerRepository.getListOtherOptionAnswerContent(o.getId());
                                            OpenAnswerReportModel openAnswerReportModel;
                                            for (Answer a : answerList) {
                                                openAnswerReportModel = new OpenAnswerReportModel();
                                                openAnswerReportModel.setAnswerContent(a.getAnswerContent());
                                                openAnswerReportModel.setConductDate(a.getCreateDate());
                                                ynAnswerReportModel.getListOptionsOther().add(openAnswerReportModel);
                                            }
                                        } else {
                                            totalPoint += (ynAnswerReportModel.getOptionPoint() * ynAnswerReportModel.getSelectedCount());
                                            totalSelected += ynAnswerReportModel.getSelectedCount();
                                        }
                                        ynQuestionReportModel.getYnAnswerReportModelList().add(ynAnswerReportModel);
                                    }
                                    questionAvgPoint = totalPoint / totalSelected;
                                    ynQuestionReportModel.setQuestionAvgPoint(questionAvgPoint);
                                    CriteriaReportModel tmp = findCriteriaReportModelByCriteriaId(
                                            criteriaReportModels, q.getCriteriaByCriteriaId().getId());
                                    if (tmp != null) {
                                        tmp.getYnQuestionReportModels().add(ynQuestionReportModel);
                                    }
                                }
                                break;
                            }
                            case QuestionType.TEXT:
                            case QuestionType.TEXT_AREA: {
                                OpenQuestionReportModel openQuestionReportModel = new OpenQuestionReportModel();
                                openQuestionReportModel.setFeedbackId(f.getId());
                                openQuestionReportModel.setFeedbackName(f.getFeedbackName());
                                openQuestionReportModel.setQuestionContent(q.getQuestionContent());
                                openQuestionReportModel.setSuggestion(q.getSuggestion());
                                int countResponse = answerRepository.countNumberOfQuestionsResponse(q.getId());
                                openQuestionReportModel.setResponseCount(countResponse);
                                for (Optionn o : q.getOptionsById()) {
                                    List<Answer> answerList = answerRepository.getAllByOptionnByOptionnIdId(o.getId());
                                    OpenAnswerReportModel openAnswerReportModel;
                                    for (Answer a : answerList) {
                                        openAnswerReportModel = new OpenAnswerReportModel();
                                        openAnswerReportModel.setConductDate(a.getCreateDate());
                                        openAnswerReportModel.setAnswerContent(a.getAnswerContent());
                                        openQuestionReportModel.getOpenAnswerReportModels().add(openAnswerReportModel);
                                    }
                                    break;
                                }
                                CriteriaReportModel tmp = findCriteriaReportModelByCriteriaId(
                                        criteriaReportModels, q.getCriteriaByCriteriaId().getId());
                                if (tmp != null) {
                                    tmp.getOpenQuestionReportModels().add(openQuestionReportModel);
                                }
                                break;
                            }
                        }

                    }
                }
            }
            removeUnnecessaryCriteria(criteriaReportModels);
            calculateCriteriaAvg(criteriaReportModels);
            reportSemesterModel.setCriteriaReportModelList(criteriaReportModels);
            calculateSemPoint(reportSemesterModel);
            return reportSemesterModel;
        }
    }

    @Override
    public List<Clazz> findClazzByLectureId(int lectureId) {
        List<Clazz> clazzList = clazzRepository.findByUserByLecturerIdId(lectureId);
        return clazzList;
    }

    private void calculateCriteriaAvg(List<CriteriaReportModel> criteriaReportModels) {
        for (CriteriaReportModel c : criteriaReportModels) {
            double totalQuestionPoint = 0;
            List<YNQuestionReportModel> ynQuestionReportModels = c.getYnQuestionReportModels();
            if (ynQuestionReportModels != null && !ynQuestionReportModels.isEmpty()) {
                for (YNQuestionReportModel q : ynQuestionReportModels) {
                    totalQuestionPoint += q.getQuestionAvgPoint();
                }
                double numOfYNQuestion = ynQuestionReportModels.size();
                c.setAverageCriteriaPoint(totalQuestionPoint / numOfYNQuestion);
            }
        }
    }

    private void calculateSemPoint(ReportSemesterModel reportSemesterModel) {
        double semAvgPoint = 0;
        double totalCriteriaPoint = 0;
        for (CriteriaReportModel c : reportSemesterModel.getCriteriaReportModelList()) {
            totalCriteriaPoint += c.getAverageCriteriaPoint();
        }
        semAvgPoint = totalCriteriaPoint / reportSemesterModel.getCriteriaReportModelList().size();
        reportSemesterModel.setAverageSemPoint(semAvgPoint);
    }

    private CriteriaReportModel findCriteriaReportModelByCriteriaId(List<CriteriaReportModel> criteriaReportModels, int criteriaId) {
        for (CriteriaReportModel c : criteriaReportModels) {
            if (c.getCriteriaId() == criteriaId) {
                return c;
            }
        }
        return null;
    }

    private void removeUnnecessaryCriteria(List<CriteriaReportModel> criteriaReportModels) {
        for (CriteriaReportModel c : criteriaReportModels) {
            if ((c.getOpenQuestionReportModels() == null || c.getOpenQuestionReportModels().isEmpty())
                    && (c.getYnQuestionReportModels() == null || c.getYnQuestionReportModels().isEmpty())) {
                criteriaReportModels.remove(c);
            }
        }
    }
}
