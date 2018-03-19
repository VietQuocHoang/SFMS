package com.sample.sfms.repository;

import com.sample.sfms.entity.Semester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by MyPC on 09/03/2018.
 *
 */

@Repository
public interface SemesterRepository extends JpaRepository<Semester, Integer> {
    List<Semester> findByTitleContains(String title);
}
