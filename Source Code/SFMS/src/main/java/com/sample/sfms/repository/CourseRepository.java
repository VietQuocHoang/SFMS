package com.sample.sfms.repository;

import com.sample.sfms.entity.Course;
import com.sample.sfms.entity.MajorCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by MyPC on 20/02/2018.
 */
@Repository
public interface CourseRepository extends JpaRepository<Course, Integer>{
    Course findByMajorCoursesById(MajorCourse majorCourse);
}
