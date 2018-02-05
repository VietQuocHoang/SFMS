package com.sample.sfms.service.interf;

import com.sample.sfms.entity.User;

public interface UserService {

    User findUserByMail(String email);

    void saveUser(User user);
}
