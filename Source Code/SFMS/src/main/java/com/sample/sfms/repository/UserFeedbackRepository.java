package com.sample.sfms.repository;

import com.sample.sfms.entity.UserFeedback;
import com.sample.sfms.entity.UserFeedbackPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

/**
 * Created by MyPC on 05/03/2018.
 */
@Repository
public interface UserFeedbackRepository extends JpaRepository<UserFeedback, UserFeedbackPK> {

    List<UserFeedback> findByFeedbackByFeedbackId_Id(int id);
    List<UserFeedback> findByUserByUserId_Id(int id);

    @Modifying
    @Query("Select u from UserFeedback u where u.userByUserId.id=:userId and u.conducted=false and u.feedbackByFeedbackId.removed = false and u.feedbackByFeedbackId.isTemplate = false")
    List<UserFeedback> findNotConductedFeedbacksByUserId(@Param("userId") int id);

    @Modifying
    @Query("Select u " +
            "from UserFeedback u " +
            "where u.userByUserId.id=:userId " +
            "and u.conducted=false " +
            "and u.feedbackByFeedbackId.startDate < :currDate " +
            "and u.feedbackByFeedbackId.endDate > :currDate " +
            "and u.conductor=true " +
            "and u.feedbackByFeedbackId.isTemplate = false " +
            "and u.feedbackByFeedbackId.removed = false")
    List<UserFeedback> findNotConductedFeedbacksByUserIdAndCurrDate(@Param("userId") int id, @Param("currDate") Date currDate);
    @Modifying
    @Query("Select u from UserFeedback u where u.userByUserId.id=:userId and u.feedbackByFeedbackId.removed=false and u.feedbackByFeedbackId.isTemplate = false")
    List<UserFeedback> findFeedbacksByUserId(@Param("userId") int id);


    @Query("Select u " +
            "from UserFeedback u " +
            "where u.userByUserId.id=:userId " +
            "and u.feedbackByFeedbackId.id=:feedbackId " +
            "and u.feedbackByFeedbackId.removed = false " +
            "and u.feedbackByFeedbackId.isTemplate = false")
    UserFeedback findUserFeedbackByUserAndFeedback(@Param("userId") int userId, @Param("feedbackId") int feedbackId);

    @Query("Select count(u) from UserFeedback u where u.userByUserId.id=:userId and u.conducted=true and u.feedbackByFeedbackId.isTemplate = false and u.feedbackByFeedbackId.removed=false")
    int countNumberOfConductedFeedbackForUser(@Param("userId") int userId);

    @Query("Select count(u) from UserFeedback u where u.userByUserId.id=:userId and u.conducted=false and u.feedbackByFeedbackId.isTemplate = false and u.feedbackByFeedbackId.removed=false")
    int countNumberOfNotConductedFeedbackForUser(@Param("userId") int userId);

    @Query("select u " +
            "from UserFeedback u " +
            "where u.conducted=false " +
            "and u.feedbackByFeedbackId.endDate between :today and :twoDayAhead " +
            "and u.conductor = true " +
            "and u.feedbackByFeedbackId.isTemplate = false " +
            "and u.feedbackByFeedbackId.removed = false")
    List<UserFeedback> getListUserFeedbackNotDoFeedbackYet(@Param("today") Date today, @Param("twoDayAhead") Date twoDayAhead);
}
