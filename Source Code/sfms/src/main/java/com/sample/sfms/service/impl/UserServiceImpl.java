package com.sample.sfms.service.impl;

import com.sample.sfms.repository.RoleRepository;
import com.sample.sfms.repository.UserRepository;
import com.sample.sfms.service.interf.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
      /*  user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRole(roleRepository.findByRoleName(user.getRole().getRoleName()));
        userRepository.save(user);*/
    }
}