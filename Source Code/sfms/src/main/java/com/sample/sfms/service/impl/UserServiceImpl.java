package com.sample.sfms.service.impl;

import com.sample.sfms.entity.Role;
import com.sample.sfms.entity.User;
import com.sample.sfms.repository.RoleRepository;
import com.sample.sfms.repository.UserRepository;
import com.sample.sfms.service.interf.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("userService")
public class UserServiceImpl implements UserService {

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
    public void saveUser(User user) {
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

}