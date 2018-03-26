package com.sample.sfms.api;

import com.fasterxml.jackson.annotation.JsonView;
import com.sample.sfms.entity.Semester;
import com.sample.sfms.service.interf.ModifyFeedbackService;
import com.sample.sfms.service.interf.ReportService;
import com.sample.sfms.view.FeedbackView;
import com.sample.sfms.view.SemesterView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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


    @JsonView(SemesterView.basicSemesterView.class)
    @GetMapping("/loadAllSemesters")
    private ResponseEntity getListSemester()
    {
        return modifyFeedbackService.loadAllSemesters();
    }


}
