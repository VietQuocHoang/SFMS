package com.sample.sfms.service.interf;

import com.sample.sfms.entity.Role;
import com.sample.sfms.entity.User;

import java.util.List;

public interface UserService {

    User findUserByMail(String email);

    void saveUser(User user);

    List<Role> getListRole();
}
