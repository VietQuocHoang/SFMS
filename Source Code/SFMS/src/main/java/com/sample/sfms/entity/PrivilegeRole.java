package com.sample.sfms.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by MyPC on 19/02/2018.
 */
@Entity
@Table(name = "privilege_role", schema = "capstone", catalog = "")
public class PrivilegeRole {
    private Role roleByRoleId;
    private Privilege privilegeByPrivilegeId;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false)
    public Role getRoleByRoleId() {
        return roleByRoleId;
    }

    public void setRoleByRoleId(Role roleByRoleId) {
        this.roleByRoleId = roleByRoleId;
    }

    @ManyToOne
    @JoinColumn(name = "privilege_id", referencedColumnName = "id", nullable = false)
    public Privilege getPrivilegeByPrivilegeId() {
        return privilegeByPrivilegeId;
    }

    public void setPrivilegeByPrivilegeId(Privilege privilegeByPrivilegeId) {
        this.privilegeByPrivilegeId = privilegeByPrivilegeId;
    }
}
