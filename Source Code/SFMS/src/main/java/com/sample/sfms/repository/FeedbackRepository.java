package com.sample.sfms.repository;

import com.sample.sfms.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
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

    @Query("select f from Feedback f where f.semesterBySemesterId.id=:semId and f.departmentByDepartmentId.id=:depId and f.isTemplate=false and (f.typeByTypeId.id = :typeId OR :typeId = -1)")
    List<Feedback> getListFeedbackBySemIdAndDepId(@Param("semId") int semId, @Param("depId") int depId, @Param("typeId") int typeId);

    @Query("select f from Feedback f where f.semesterBySemesterId.id=:semId and f.majorByMajorId.id=:majorId and f.isTemplate=false and (f.typeByTypeId.id = :typeId OR :typeId = -1)")
    List<Feedback> getListFeedbackBySemIdAndMajorId(@Param("semId") int semId, @Param("majorId") int majorId, @Param("typeId") int typeId);

    @Query("select f from Feedback f where f.semesterBySemesterId.id=:semId and f.clazzByClazzId.id=:classId and f.isTemplate=false and (f.typeByTypeId.id = :typeId OR :typeId = -1)")
    List<Feedback> getListFeedbackBySemIdAndClassId(@Param("semId") int semId, @Param("classId") int classId, @Param("typeId") int typeId);

    @Query("select f from Feedback f where f.semesterBySemesterId.id=:semId and f.courseByCourseId.id=:courseId and f.isTemplate=false and (f.typeByTypeId.id = :typeId OR :typeId = -1)")
    List<Feedback> getListFeedbackBySemIdAndCourseId(@Param("semId") int semId, @Param("courseId") int courseId, @Param("typeId") int typeId);

    @Query("select f from Feedback f where f.startDate < :date and f.endDate > :date and isPublished=1")
    List<Feedback> getListOnGoingFeedbackByDate(@Param("date") Date date);

    @Query("select count(f) from Feedback f where f.startDate < :date and f.endDate > :date and isPublished=1")
    int countOnGoingFeedbackByDate(@Param("date") Date date);

    @Query(value = "select count(f) from Feedback f where f.clazzByClazzId in (select c.id from Clazz c where c.userByLecturerId.id=:lecturerId)")
    int countFeedbackBeingConductedOnLecturer(@Param("lecturerId") int lecturerId);

    @Query(value = "select count(f) from Feedback f where f.clazzByClazzId in (select c.id from Clazz c where c.userByLecturerId.id=:lecturerId) and f.startDate < :currDate and f.endDate > :currDate")
    int countOnGoingFeedbackBeingConductedOnLecturer(@Param("lecturerId") int lecturerId, @Param("currDate") Date currDate);

    @Query(value = "select count(f) from Feedback f where f.clazzByClazzId in (select c.id from Clazz c where c.userByLecturerId.id=:lecturerId) and f.endDate < :currDate")
    int countFinishedFeedbackBeingConductedOnLecturer(@Param("lecturerId") int lecturerId, @Param("currDate") Date currDate);

    @Query(value = "select count(f) from Feedback f")
    int countAllFeedback();

    int countAllByTypeByTypeIdId(int typeId);

    @Query(value = "select count(f) from Feedback f where f.departmentByDepartmentId.id=:depId and f.startDate < :currDate and f.endDate > :currDate")
    int countOnGoingFeedbackForDepartment(@Param("depId") int depId, @Param("currDate") Date currDate);

    @Query(value = "select count(f) from Feedback f where f.departmentByDepartmentId.id=:depId and f.endDate < :currDate")
    int countFinishedFeedbackForDepartment(@Param("depId") int depId, @Param("currDate") Date currDate);

    @Query("select f from Feedback f, Clazz c where " +
            "f.isTemplate = false AND f.clazzByClazzId.id = c.id AND "+
            "(f.typeByTypeId.id = :type OR :type = -1) AND "+
            "c.courseByCourseId.id = :courseId AND c.userByLecturerId.id = :userId and f.semesterBySemesterId.id = :semId")
    List<Feedback> findByUserCourse(@Param("courseId")int courseId,
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

    @Modifying
    @Query("update Feedback f set f.isTemplate = false where f.id = ?1")
    int deactiveTemplate(int templateId);


    @Query("select f from Feedback f where f.isPublished=true and f.startDate < :currDate and f.endDate > :currDate and f.id=:id")
    Feedback findFeedbackToConduct(@Param("id") int id, @Param("currDate") Date currDate);


    @Query("Select f from Feedback f where f.isTemplate = 0")
    List<Feedback> findAllFeedbackNotTemplate();
    
    @Query("select f from Feedback f where " +
            "f.isTemplate = false AND f.departmentByDepartmentId.id = :depId AND "+
            "(f.typeByTypeId.id = :type OR :type = -1) AND "+
            "f.semesterBySemesterId.id = :semId")
    List<Feedback> findByDepartment(@Param("depId")int depId,
                                    @Param("type")int type,
                                    @Param("semId") int semId);

    @Query("select f from Feedback f where " +
            "f.isTemplate = false AND f.majorByMajorId.id = :majorId AND "+
            "(f.typeByTypeId.id = :type OR :type = -1) AND "+
            "f.semesterBySemesterId.id = :semId")
    List<Feedback> findByMajor(@Param("majorId")int majorId,
                                    @Param("type")int type,
                                    @Param("semId") int semId);

    @Query("select f from Feedback f where " +
            "f.isTemplate = false AND f.courseByCourseId.id = :courseId AND "+
            "(f.typeByTypeId.id = :type OR :type = -1) AND "+
            "f.semesterBySemesterId.id = :semId")
    List<Feedback> findByCourse(@Param("courseId")int courseId,
                                    @Param("type")int type,
                                    @Param("semId") int semId);
}
