package com.sample.sfms.service.interf;

public interface UserService {

    User findUserByMail(String email);

    void saveUser(User user);
}
