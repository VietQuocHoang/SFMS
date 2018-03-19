package com.sample.sfms.controller;

import com.sample.sfms.model.report.reportSemester.ReportSemesterModel;
import com.sample.sfms.service.interf.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/reports")
public class ReportController {
    @Autowired
    private ReportService reportService;

    @GetMapping
    private ModelAndView viewListReport() {
        ModelAndView mav = new ModelAndView("view-report");
        mav.addObject("feedbackTarget", reportService.loadListFeedbackTargetWrapper());
        return mav;
    }

    @GetMapping("/semester-detail")
    private ModelAndView semesterDetail(@RequestParam("typeId") int typeId, @RequestParam("semId") int semId, @RequestParam("targetId") int targetId) {
        ModelAndView mav = new ModelAndView("view-report-semester");
        ReportSemesterModel reportSemesterModel = reportService.getReportSemesterDetail(typeId, semId, targetId);
        mav.addObject("report", reportSemesterModel);
        return mav;
    }
}
