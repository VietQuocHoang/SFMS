package com.sample.sfms.repository;

import com.sample.sfms.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByMail(String email);
    List<User> findByBirthContaining (String q);
}
