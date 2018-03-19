package com.sample.sfms.repository;

import com.sample.sfms.entity.Course;
import com.sample.sfms.entity.Major;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by MyPC on 20/02/2018.
 */
@Repository
public interface CourseRepository extends JpaRepository<Course, Integer>{
//        Course findByMajorCoursesById(MajorCourse majorCourse);

        @Query("select c from Course c where " +
                "EXISTS (SELECT f FROM Feedback f WHERE f.courseByCourseId.id = c.id)")
        List<Course> filtering ();
        List<Course> findByNameContains(String name);
        List<Course> findByNameContainsOrCodeContains(String name, String code);
        List<Course> findByNameContainsOrCodeContainsAndMajorByMajorId_NameContainsOrMajorByMajorId_CodeContains(String coursename, String coursecode, String majorname, String majorcode);

        @Query("select c from Course c where " +
                "CONCAT(c.name,' ',c.code) LIKE :coursename AND c.majorByMajorId IN " +
                "(select m from Major m where CONCAT(m.name,' ',m.code) LIKE :majorname)")
        List<Course> filteringver2 (@Param("coursename") String coursename, @Param("majorname") String majorname);
//        @Query("select c from Course c where " +
//                "CONCAT(c.name,' ',c.code) LIKE :coursename")
//        List<Course> filteringver2 (@Param("coursename") String coursename);
}
