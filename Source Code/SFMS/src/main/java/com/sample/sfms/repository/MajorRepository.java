package com.sample.sfms.repository;

import com.sample.sfms.entity.Major;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by MyPC on 20/02/2018.
 */
@Repository
public interface MajorRepository extends JpaRepository<Major, Integer> {

}
