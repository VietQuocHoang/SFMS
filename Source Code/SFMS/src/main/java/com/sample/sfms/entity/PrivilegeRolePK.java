package com.sample.sfms.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by MyPC on 19/02/2018.
 */
public class PrivilegeRolePK implements Serializable {
    private int roleId;
    private int privilegeId;

    @Column(name = "role_id")
    @Id
    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Column(name = "privilege_id")
    @Id
    public int getPrivilegeId() {
        return privilegeId;
    }

    public void setPrivilegeId(int privilegeId) {
        this.privilegeId = privilegeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PrivilegeRolePK that = (PrivilegeRolePK) o;

        if (roleId != that.roleId) return false;
        if (privilegeId != that.privilegeId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = roleId;
        result = 31 * result + privilegeId;
        return result;
    }
}
