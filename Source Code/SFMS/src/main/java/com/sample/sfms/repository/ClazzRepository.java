package com.sample.sfms.repository;

import com.sample.sfms.entity.Clazz;
import com.sample.sfms.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by MyPC on 20/02/2018.
 */
@Repository
public interface ClazzRepository extends JpaRepository<Clazz, Integer> {
    @Query("select c from Clazz c where " +
            "CONCAT(c.courseByCourseId.majorByMajorId.name,' ',c.courseByCourseId.majorByMajorId.code ) LIKE :majorname " +
            "AND CONCAT(c.courseByCourseId.name,' ',c.courseByCourseId.code) LIKE :coursename " +
            "AND c.semesterBySemesterId.title LIKE :semestertitle " +
            "AND CONCAT(c.userByLecturerId.fullname,' - ',c.userByLecturerId.code) LIKE :lecturerfullname")
    List<Clazz> filtering(@Param("majorname") String majorname,
                          @Param("coursename") String coursename,
                          @Param("semestertitle") String semestertitle,
                          @Param("lecturerfullname") String lecturerfullname);

    List<Clazz> findByUserByLecturerIdId(int lectureId);

    @Query("select c from Clazz c where " +
            "c.courseByCourseId IN " +
            "(select co from Course co where CONCAT(co.name,' ',co.code) LIKE :coursekey AND co.majorByMajorId IN " +
            "(select m from Major m where CONCAT(m.name,' ',m.code) LIKE :majorkey)) " +
            "AND c.semesterBySemesterId IN (select s from Semester s where s.title LIKE :semesterkey) " +
            "AND c.userByLecturerId IN " +
            "(select u from User u where CONCAT(u.fullname,' ',u.code) LIKE :lecturerkey " +
            "AND u.majorByMajorId IN (select m from Major m where CONCAT(m.name,' ',m.code) LIKE :majorkey) " +
//            "AND u.roleByRoleId IN (select r from Role r where r.roleName IN :rolenames)" +
            ")" +
            "")
    List<Clazz> filteringver2(@Param("majorkey") String majorKey,
                              @Param("coursekey") String courseKey,
                              @Param("semesterkey") String semesterKey,
                              @Param("lecturerkey") String lecturerKey
//                              ,@Param("rolenames") String roleNames
    );


    List<Clazz> findByCourseByCourseId(Course course);

    @Query(value = "Select u.id as userId,u.fullname, co.id as courseId, co.name, co.code " +
            "from " +
            "(SELECT u.* " +
            "FROM capstone.user u, capstone.role r " +
            "where r.id = u.role_id and r.id=1) u, capstone.clazz cl, capstone.course co " +
            "where u.id = cl.lecturer_id and cl.course_id = co.id " +
            "group by co.id", nativeQuery = true)
    List<Object[]> findAllCourseCorrespondingToEachLecturerTaught();

    @Query("select distinct c from Feedback f, Clazz c where " +
            "f.isTemplate = false AND f.clazzByClazzId.id = c.id AND "+
            "(f.typeByTypeId.id = :type OR :type = -1) AND "+
            "c.courseByCourseId.id = :courseId AND c.userByLecturerId.id = :userId and c.semesterBySemesterId.id = :semId")
    List<Clazz> findListClassByCourseLecturerSemester( @Param("type")int type, @Param("userId") int userId, @Param("courseId") int courseId, @Param("semId") int semesterId);

    @Query(value = "Select u.id as userId,u.fullname, co.id as courseId, co.name, co.code " +
            "from " +
            "(SELECT u.* " +
            "FROM capstone.user u, capstone.role r " +
            "where r.id = u.role_id and r.id=1) u, capstone.clazz cl, capstone.course co " +
            "where u.id = cl.lecturer_id and cl.course_id = co.id and u.id = :lecturerId " +
            "group by co.id", nativeQuery = true)
    List<Object[]> findAllCourseCorrespondingToLecturer(@Param("lecturerId") int lecturerId);

}
