package com.sample.sfms.repository;

import com.sample.sfms.entity.Feedback;
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

    @Query("select f from Feedback f where f.semesterBySemesterId.id=:semId and f.departmentByDepartmentId.id=:depId")
    List<Feedback> getListFeedbackBySemIdAndDepId(@Param("semId") int semId, @Param("depId") int depId);

    @Query("select f from Feedback f where f.semesterBySemesterId.id=:semId and f.majorByMajorId.id=:majorId")
    List<Feedback> getListFeedbackBySemIdAndMajorId(@Param("semId") int semId, @Param("majorId") int majorId);

    @Query("select f from Feedback f where f.semesterBySemesterId.id=:semId and f.clazzByClazzId.id=:classId")
    List<Feedback> getListFeedbackBySemIdAndClassId(@Param("semId") int semId, @Param("classId") int classId);

    @Query("select f from Feedback f where f.semesterBySemesterId.id=:semId and f.courseByCourseId.id=:courseId")
    List<Feedback> getListFeedbackBySemIdAndCourseId(@Param("semId") int semId, @Param("courseId") int courseId);

    @Query("select f from Feedback f, Clazz c where " +
            "f.isTemplate = false AND f.clazzByClazzId.id = c.id AND "+
            "(f.typeByTypeId.id = :type OR :type = -1) AND "+
            "c.courseByCourseId.id = :courseId AND c.userByLecturerId.id = :userId and f.semesterBySemesterId.id = :semId")
    List<Feedback> findByUserCource(@Param("courseId")int courseId,
                                    @Param("userId")int userId,
                                    @Param("type")int type,
                                    @Param("semId") int semId);

   /* @Query("select new com.sample.sfms.model.FeedbackReportModel(c.criteria, sum(o.point), count(a.id))" +
            "from Question q, Optionn o, Answer a, Criteria c " +
            "where q.feedbackByFeedbackId.id  = :feedbackId and " +
            "q.criteriaByCriteriaId.id = c.id and " +
            "q.id = o.questionByQuestionId.id and " +
            "o.id = a.optionnByOptionnId.id")
    List<FeedbackReportModel> statistics(@Param("feedbackId")int feedbackId);*/
}
