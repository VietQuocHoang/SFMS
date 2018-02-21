package com.sample.sfms.repository;

import com.sample.sfms.entity.PrivilegeRole;
import com.sample.sfms.entity.PrivilegeRolePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by MyPC on 19/02/2018.
 */
@Repository
public interface PrivilegeRoleRepository extends JpaRepository<PrivilegeRole, PrivilegeRolePK> {

}
