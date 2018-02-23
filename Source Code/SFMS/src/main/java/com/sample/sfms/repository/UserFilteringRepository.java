package com.sample.sfms.repository;

import com.sample.sfms.entity.Clazz;
import com.sample.sfms.entity.Major;
import com.sample.sfms.entity.StudentClazz;
import com.sample.sfms.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by MyPC on 23/02/2018.
 */
@Repository
public interface UserFilteringRepository extends JpaRepository<User, Integer> {
    List<User> findByMajorByMajorId(Major major);
    List<User> findByClazzesByIdContains(List<Clazz> clazz);
    List<User> findByStudentClazzesById(List<StudentClazz> studentClazzes);
}
