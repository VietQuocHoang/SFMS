package com.sample.sfms.service.interf;

import com.sample.sfms.entity.Feedback;
import com.sample.sfms.model.dashboard.DashboardWrapperModel;

import java.util.List;

public interface DashboardService {

    List<Feedback> getListOnGoingFeedbackAtTheMoment();

    DashboardWrapperModel getDashboardData();

}
