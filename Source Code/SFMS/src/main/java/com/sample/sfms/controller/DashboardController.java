package com.sample.sfms.controller;

import com.sample.sfms.model.dashboard.DashboardWrapperModel;
import com.sample.sfms.service.interf.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    private DashboardService dashboardService;

    @Autowired
    public void setDashboardService(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping
    private ModelAndView dashboard() {
        ModelAndView mav = new ModelAndView();
        DashboardWrapperModel model = dashboardService.getDashboardData();
        if (model == null) {
            mav.setViewName("forbidden");
        } else {
            mav.addObject("dashboardObj", model);
            mav.setViewName("dashboard");
        }
        return mav;
    }

}
