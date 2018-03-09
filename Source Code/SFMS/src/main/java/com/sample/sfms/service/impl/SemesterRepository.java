package com.sample.sfms.service.impl;

import com.sample.sfms.entity.Semester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by MyPC on 09/03/2018.
 *
 */

@Repository
public interface SemesterRepository extends JpaRepository<Semester, Integer> {
}
