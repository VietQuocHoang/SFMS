package com.sample.sfms.service.interf;

import com.sample.sfms.entity.UserFeedback;

import java.util.List;

public interface MailService {
    List<UserFeedback> getListUserFeedbackNotDoFeedbackYet();

    void notifyUser();
}
