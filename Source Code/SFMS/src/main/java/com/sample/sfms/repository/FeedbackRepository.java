package com.sample.sfms.repository;

import com.sample.sfms.entity.Feedback;
import com.sample.sfms.model.FeedbackReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@org.springframework.stereotype.Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {

    @Query("select f from Feedback f where f.isTemplate = :isTemplate")
    List<Feedback> findByIsTemplate(@Param("isTemplate")boolean isTemplate);

    Feedback findById(int id);

    @Query("select f from Feedback f, Semester s where " +
            "f.departmentByDepartmentId.id = :depID AND f.semesterBySemesterId.id = :semesterID")
    int findByDepIdAndSemesterId(@Param("depID")int depID,
                                 @Param("semesterID")String semesterID);

    @Query("select f from Feedback f, Semester s where " +
            "f.courseByCourseId.id = :courseID AND f.semesterBySemesterId.id = :semesterID")
    int findByCourseIdAndSemesterId(@Param("courseID")int courseID,
                                    @Param("semesterID")String semesterID);

    @Query("select f from Feedback f, Semester s where " +
            "f.majorByMajorId.id = :majorID AND f.semesterBySemesterId.id = :semesterID")
    int findByMajorIdAndSemesterId(@Param("majorID")int majorID,
                                   @Param("semesterID")String semesterID);

    @Query("select f from Feedback f, Semester s, Department d where " +
            "f.departmentByDepartmentId.id = d.id AND f.semesterBySemesterId.id = s.id")
    List<Feedback> getListDepFeedback();

    @Query("select f from Feedback f, Semester s, Major j where " +
            "f.majorByMajorId.id = j.id AND f.semesterBySemesterId.id = s.id")
    List<Feedback> getListMajorFeedback();

    @Query("select f from Feedback f, Semester s, Course c where " +
            "f.courseByCourseId.id = c.id AND f.semesterBySemesterId.id = s.id")
    List<Feedback> getListCourseFeedback();

    @Query("select f from Feedback f, Semester s, Clazz c where " +
            "f.clazzByClazzId.id = c.id AND f.semesterBySemesterId.id = s.id")
    List<Feedback> getListClassFeedback();

    @Query("select f from Feedback f, Clazz c where " +
            "f.isTemplate = false AND f.clazzByClazzId.id = c.id AND "+
            "(f.typeByTypeId.id = :type OR :type = -1) AND "+
            "f.semesterBySemesterId.id = :semesterId AND "+
            "c.courseByCourseId.id = :courseId AND c.userByLecturerId.id = :userId")
    List<Feedback> findByUserCource(@Param("courseId")int courseId,
                                    @Param("courseId")int userId,
                                    @Param("type")int type,
                                    @Param("semesterId")int semesterId);

    @Query("select com.sample.sfms.model.FeedbackReport(c.criteria, sum(o.point), count(a.id)) " +
            "from Question q, Optionn o, Answer a, Criteria c " +
            "where q.feedbackByFeedbackId.id  = :feedbackId and " +
            "q.criteriaByCriteriaId.id = c.id and " +
            "q.id = o.questionByQuestionId.id and " +
            "o.id = a.optionnByOptionnId.id")
    List<FeedbackReport> statistics(@Param("feedbackId")int feedbackId);
}
