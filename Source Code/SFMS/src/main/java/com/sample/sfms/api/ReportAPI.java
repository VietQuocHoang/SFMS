package com.sample.sfms.api;

import com.fasterxml.jackson.annotation.JsonView;
import com.sample.sfms.entity.Semester;
import com.sample.sfms.model.FeedbackReporStatistictModel;
import com.sample.sfms.model.FeedbackReportModel;
import com.sample.sfms.service.interf.ModifyFeedbackService;
import com.sample.sfms.service.interf.ReportService;
import com.sample.sfms.view.FeedbackView;
import com.sample.sfms.view.SemesterView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Binh Nguyen on 25-Feb-18.
 */
@RestController
@RequestMapping("/api/reports")
public class ReportAPI  {

    @Autowired
    ReportService reportService;

    @Autowired
    ModifyFeedbackService modifyFeedbackService;

    @GetMapping
    private List<Object> getListReport(@RequestParam("type") String type)
    {
        return reportService.loadListReport(type);
    }


    @JsonView(SemesterView.reportAllSemesterView.class)
    @GetMapping("/loadAllSemesters")
    private ResponseEntity getListSemester()
    {
        return modifyFeedbackService.loadAllSemesters();
    }

    @GetMapping("/semesterStatistic")
    private ResponseEntity getSemesterStatistic(@RequestParam("userId") int userId,
                                                @RequestParam("targetId") int targetId,
                                                @RequestParam("typeId") int typeId,
                                                @RequestParam(value="semesterId") List<Integer> semesterIds)
    {
        List<FeedbackReporStatistictModel> feedbackReporStatistictModels = new ArrayList<>();
        for (int semId : semesterIds) {
            List<FeedbackReportModel> feedbackReportModel = reportService.loadReportDetail(targetId,userId,typeId,semId);
            FeedbackReporStatistictModel feedbackReporStatistictModel = new FeedbackReporStatistictModel(semId, feedbackReportModel);
            feedbackReporStatistictModels.add(feedbackReporStatistictModel);
            System.out.println("feedbackReporStatistictModels" + feedbackReporStatistictModel.getData().get(0).getSum());
        }

        return new ResponseEntity(feedbackReporStatistictModels, HttpStatus.OK);
    }
}
