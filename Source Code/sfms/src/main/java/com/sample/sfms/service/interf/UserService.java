package com.sample.sfms.service.interf;

import com.sample.sfms.entity.Role;
import com.sample.sfms.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;
public interface UserService {
    User findUserByMail(String email);

    ResponseEntity saveUser(User user);

    List<Role> getListRole();

    Iterable<User> findAll();

    List<User> search(String q);

    User findOne(int id);

    void save(User user);

    void delete(int id);

    User getCurrentAuthenticatedUser();
}
