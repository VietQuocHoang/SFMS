package com.sample.sfms.service.interf;

import com.sample.sfms.model.feedback.FeedbackTargetWrapper;
import com.sample.sfms.model.report.reportSemester.ReportSemesterModel;

import java.util.List;

/**
 * Created by MyPC on 21/02/2018.
 */

public interface ReportService {
    List<Object> loadListReport (String type);

    FeedbackTargetWrapper loadListFeedbackTargetWrapper();

    ReportSemesterModel getReportSemesterDetail(int typeId, int semId, int targetId);
}
