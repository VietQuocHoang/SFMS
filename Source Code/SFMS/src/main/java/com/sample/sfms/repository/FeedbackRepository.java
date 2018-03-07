package com.sample.sfms.repository;

import com.sample.sfms.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@org.springframework.stereotype.Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {
   List<Feedback> findByIsTemplate(boolean isTemplate);

   Feedback findById(int id);

   @Query("select f from Feedback f, Semester s where " +
           "f.departmentByDepartmentId = :depID AND f.semesterBySemesterId = :semesterID")
   int findByDepIdAndSemesterId(@Param("depID")int depID,
                              @Param("semesterID")String semesterID);

   @Query("select f from Feedback f, Semester s where " +
           "f.courseByCourseId = :courseID AND f.semesterBySemesterId = :semesterID")
   int findByCourseIdAndSemesterId(@Param("courseID")int courseID,
                                @Param("semesterID")String semesterID);

   @Query("select f from Feedback f, Semester s where " +
           "f.majorByMajorId = :majorID AND f.semesterBySemesterId = :semesterID")
   int findByMajorIdAndSemesterId(@Param("majorID")int majorID,
                                   @Param("semesterID")String semesterID);

   @Query("select f from Feedback f, Semester s, Department d where " +
           "f.departmentByDepartmentId = d.id AND f.semesterBySemesterId = s.id")
   List<Feedback> getListDepFeedback();

   @Query("select f from Feedback f, Semester s, Major j where " +
           "f.majorByMajorId = j.id AND f.semesterBySemesterId = s.id")
   List<Feedback> getListMajorFeedback();

   @Query("select f from Feedback f, Semester s, Course c where " +
           "f.courseByCourseId = c.id AND f.semesterBySemesterId = s.id")
   List<Feedback> getListCourseFeedback();

   @Query("select f from Feedback f, Semester s, Clazz c where " +
           "f.clazzByClazzId = c.id AND f.semesterBySemesterId = s.id")
   List<Feedback> getListClassFeedback();
}
