package com.sample.sfms.repository;

import com.sample.sfms.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByRoleName(String role);
    Role findById(int id);


    List<Role> findByRoleNameIn(Set<String> rolenames);
}
