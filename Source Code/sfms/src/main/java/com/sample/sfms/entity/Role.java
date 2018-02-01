package com.sample.sfms.entity;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Role {
    private int id;
    private String roleName;
    private List<RolePrivilege> rolePrivileges;

    public Role() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    public List<RolePrivilege> getRolePrivileges() {
        return rolePrivileges;
    }

    public void setRolePrivileges(List<RolePrivilege> rolePrivileges) {
        this.rolePrivileges = rolePrivileges;
    }
}
