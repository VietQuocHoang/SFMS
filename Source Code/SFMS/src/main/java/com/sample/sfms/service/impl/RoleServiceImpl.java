package com.sample.sfms.service.impl;

import com.sample.sfms.entity.Role;
import com.sample.sfms.repository.RoleRepository;
import com.sample.sfms.service.interf.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.UnexpectedRollbackException;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service("roleService")
public class RoleServiceImpl implements RoleService{

    static Logger logger = Logger.getLogger(RoleServiceImpl.class.getName());
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> getListRole() {
        return roleRepository.findAll();
    }

    @Override
    public Role getRoleByRoleId(int id) {
        return roleRepository.findById(id);
    }

    @Override
    public ResponseEntity<Role> saveRole(int id, String roleName) {
        Role role = new Role();
        role.setId(id);
        role.setRoleName(roleName);
        ResponseEntity<Role> response;
        try {
            roleRepository.save(role);
            response = new ResponseEntity<>(HttpStatus.CREATED);
            return response;
        } catch (UnexpectedRollbackException e){
            logger.log(Level.FINE, e.toString());
            response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            return response;
        }
    }

    @Override
    public ResponseEntity<Role> saveRole(String roleName) {
        Role role = new Role();
        role.setRoleName(roleName);
        try {
            roleRepository.save(role);
            return new ResponseEntity<>(role, HttpStatus.CREATED);
        } catch (UnexpectedRollbackException e){
            logger.log(Level.FINE, e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Role> removeRole(int id) {
        Role role = roleRepository.findById(id);
        if(null == role){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        try{
            roleRepository.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (UnexpectedRollbackException e){
            logger.log(Level.FINE, e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}
