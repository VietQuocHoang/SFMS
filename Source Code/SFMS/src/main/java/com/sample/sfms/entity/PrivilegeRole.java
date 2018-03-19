package com.sample.sfms.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Created by MyPC on 22/02/2018.
 */
@Entity
@Table(name = "privilege_role", schema = "capstone", catalog = "")
//@IdClass(PrivilegeRolePK.class)
public class PrivilegeRole {
    private PrivilegeRolePK privilegeRolePK;

    public PrivilegeRole() {
        privilegeRolePK = new PrivilegeRolePK();
    }

    @EmbeddedId
    public PrivilegeRolePK getPrivilegeRolePK() {
        return privilegeRolePK;
    }

    public void setPrivilegeRolePK(PrivilegeRolePK privilegeRolePK) {
        this.privilegeRolePK = privilegeRolePK;
    }

    @Transient
    public Privilege getPrivilege() {
        return getPrivilegeRolePK().getPrivilege();
    }

    public void setPrivilege(Privilege privilege) {
        getPrivilegeRolePK().setPrivilege(privilege);
    }

    @Transient
    public Role getRole() {
        return getPrivilegeRolePK().getRole();
    }

    public void setRole(Role role) {
        getPrivilegeRolePK().setRole(role);
    }

}
