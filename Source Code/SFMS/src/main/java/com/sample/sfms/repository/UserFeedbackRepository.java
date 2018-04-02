package com.sample.sfms.repository;

import com.sample.sfms.entity.UserFeedback;
import com.sample.sfms.entity.UserFeedbackPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by MyPC on 05/03/2018.
 */
@Repository
public interface UserFeedbackRepository extends JpaRepository<UserFeedback, UserFeedbackPK> {

    List<UserFeedback> findByFeedbackByFeedbackId_Id(int id);
    List<UserFeedback> findByUserByUserId_Id(int id);

    @Modifying
    @Query("Select u from UserFeedback u where u.userByUserId.id=:userId and u.conducted=false")
    List<UserFeedback> findNotConductedFeedbacksByUserId(@Param("userId") int id);

    @Modifying
    @Query("Select u from UserFeedback u where u.userByUserId.id=:userId")
    List<UserFeedback> findFeedbacksByUserId(@Param("userId") int id);


    @Query("Select u from UserFeedback u where u.userByUserId.id=:userId and u.feedbackByFeedbackId.id=:feedbackId")
    UserFeedback findUserFeedbackByUserAndFeedback(@Param("userId") int userId, @Param("feedbackId") int feedbackId);

    @Query("Select count(u) from UserFeedback u where u.userByUserId.id=:userId and u.conducted=true")
    int countNumberOfConductedFeedbackForUser(@Param("userId") int userId);

    @Query("Select count(u) from UserFeedback u where u.userByUserId.id=:userId and u.conducted=false")
    int countNumberOfNotConductedFeedbackForUser(@Param("userId") int userId);
}
