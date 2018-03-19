package com.sample.sfms.repository;

import com.sample.sfms.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * Created by MyPC on 23/02/2018.
 */
@Repository
public interface UserFilteringRepository extends JpaRepository<User, Integer> {
    List<User> findByMajorByMajorId(Major major);
    List<User> findByClazzesByIdContains(List<Clazz> clazz);
    List<User> findByStudentClazzesById(List<StudentClazz> studentClazzes);

    List<User> findByRoleByRoleIdAndMajorByMajorId(Role role, Major major);
    List<User> findByRoleByRoleId_RoleNameAndAndDepartmentByDepartmentId(String roleName, Department department);
    List<User> findByRoleByRoleId_RoleNameAndClazzesByIdContains(String roleName, List<Clazz> clazzes);
    List<User> findByRoleByRoleIdAndMajorByMajorId_CoursesByIdContains(Role role, Course courses);

    List<User> findByFullnameContainsOrCodeContains(String name, String code);

//    @Query("select u from User u where CONCAT(u.fullname,' ',u.code) LIKE :userkey AND u.majorByMajorId IN " +
//            "" +
//            " AND u.roleByRoleId IN :roles ")
//    List<User> filtering(@Param("userkey") String userkey, @Param("majors") Major[] majors, @Param("roles") Collection<Role> roles);

}
