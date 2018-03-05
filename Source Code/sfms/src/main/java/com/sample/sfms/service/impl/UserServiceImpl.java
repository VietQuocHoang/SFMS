package com.sample.sfms.service.impl;

import com.sample.sfms.entity.Role;
import com.sample.sfms.entity.User;
import com.sample.sfms.repository.RoleRepository;
import com.sample.sfms.repository.UserRepository;
import com.sample.sfms.service.interf.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.RollbackException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service("userService")
public class UserServiceImpl implements UserService {
    private static Logger logger = Logger.getLogger(UserServiceImpl.class.getName());

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User findUserByMail(String email) {

        return userRepository.findByMail(email);
    }

    @Override
    public ResponseEntity saveUser(User user) {
        String encryptPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encryptPassword);
        if (user.getId() != 0) {
            User curr = userRepository.findOne(user.getId());
            if (null == curr) {
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
        }
        try {
            userRepository.save(user);
            return new ResponseEntity(HttpStatus.OK);
        } catch (RollbackException e) {
            logger.log(Level.FINE, e.toString());
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public List<Role> getListRole() {

        return roleRepository.findAll();
    }

    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<User> search(String q) {
        return userRepository.findByBirthContaining(q);
    }

    @Override
    public User findOne(int id) {
        return userRepository.findOne(id);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void delete(int id) {
        userRepository.delete(id);
    }

    @Override
    public User getCurrentAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userRepository.findByUsername(username);
    }

}