package com.sample.sfms.repository;

import com.sample.sfms.entity.Role;
import com.sample.sfms.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByMail(String email);
    List<User> findByBirthContaining (String q);
    List<User> findByRoleByRoleId_RoleName(String roleName);
    List<User> findByRoleByRoleId_RoleNameContainsAndFullnameContainsAndMajorByMajorId_NameContains(String rolename, String fullname, String major);
    User findByUsername(String username);
    List<User> findByRoleByRoleId(Role r);
    User findByCode(String code);

    @Query(value = "select count (u) from User u")
    int countAllUser();

    @Query(value = "select count(u) from User u where u.status = 1")
    int countActiveUser();

    @Query(value = "select count(u) from User u where u.status = 0")
    int countDeactivatedUser();

    int countAllByRoleByRoleIdId(int roleId);
}
