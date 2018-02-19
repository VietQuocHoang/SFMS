package com.sample.sfms.service.interf;

import java.util.List;

public interface UserService {

    User findUserByMail(String email);

    void saveUser(User user);

    List<Role> getListRole();
}
