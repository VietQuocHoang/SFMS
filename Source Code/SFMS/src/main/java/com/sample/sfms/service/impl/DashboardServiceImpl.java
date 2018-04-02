package com.sample.sfms.service.impl;

import com.sample.sfms.define.RoleName;
import com.sample.sfms.entity.Feedback;
import com.sample.sfms.entity.Role;
import com.sample.sfms.entity.Type;
import com.sample.sfms.entity.User;
import com.sample.sfms.model.dashboard.DashboardWrapperModel;
import com.sample.sfms.model.dashboard.PieChartDataModel;
import com.sample.sfms.model.dashboard.PieChartModel;
import com.sample.sfms.repository.*;
import com.sample.sfms.service.interf.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class DashboardServiceImpl implements DashboardService {
    private UserFeedbackRepository userFeedbackRepository;
    private FeedbackRepository feedbackRepository;
    private UserRepository userRepository;
    private ClazzRepository clazzRepository;
    private RoleRepository roleRepository;
    private TypeRepository typeRepository;

    @Autowired
    public void setFeedbackRepository(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setUserFeedbackRepository(UserFeedbackRepository userFeedbackRepository) {
        this.userFeedbackRepository = userFeedbackRepository;
    }

    @Autowired
    public void setClazzRepository(ClazzRepository clazzRepository) {
        this.clazzRepository = clazzRepository;
    }

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Autowired
    public void setTypeRepository(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    @Override
    public List<Feedback> getListOnGoingFeedbackAtTheMoment() {
        return feedbackRepository.getListOnGoingFeedbackByDate(new Date(System.currentTimeMillis()));
    }

    @Override
    public DashboardWrapperModel getDashboardData() {
        User user = getCurrentAuthenticatedUser();
        if (user == null) {
            return null;
        } else {
            String role = user.getRoleByRoleId().getRoleName();
            DashboardWrapperModel dashboardWrapperModel = new DashboardWrapperModel();
            PieChartModel pieChartModel = new PieChartModel();
            pieChartModel.setTitle("Feedback bạn phải làm");
            //get data for conducted feedback
            PieChartDataModel pieChartDataModel = new PieChartDataModel();
            pieChartDataModel.setName("Feedback bạn đã hoàn thành");
            pieChartDataModel.setValue(userFeedbackRepository.countNumberOfConductedFeedbackForUser(user.getId()));
            pieChartModel.addData(pieChartDataModel);

            //get data for not conducted feedback
            pieChartDataModel = new PieChartDataModel();
            pieChartDataModel.setName("Feedback bạn chưa hoàn thành");
            pieChartDataModel.setValue(userFeedbackRepository.countNumberOfNotConductedFeedbackForUser(user.getId()));
            pieChartModel.addData(pieChartDataModel);

            dashboardWrapperModel.addPieChart(pieChartModel);
            switch (role) {
                case RoleName.LECTURER: {
                    // unfinished && finished feedback for lecture's self chart
                    pieChartModel = new PieChartModel();
                    pieChartModel.setTitle("Thống kê feedback cho lớp của bạn");

                    // Get currently ongoing feedback for lecturer's self
                    pieChartDataModel = new PieChartDataModel();
                    pieChartDataModel.setName("Feedback đang được thực hiện cho các lớp của bạn");
                    pieChartDataModel.setValue(feedbackRepository.countOnGoingFeedbackBeingConductedOnLecturer(user.getId(), new Date(System.currentTimeMillis())));
                    pieChartModel.addData(pieChartDataModel);
                    // Get finished feedback for lecturer's self
                    pieChartDataModel = new PieChartDataModel();
                    pieChartDataModel.setName("Feedback đã được hoàn thành cho các lớp của bạn");
                    pieChartDataModel.setValue(feedbackRepository.countFinishedFeedbackBeingConductedOnLecturer(user.getId(), new Date(System.currentTimeMillis())));
                    pieChartModel.addData(pieChartDataModel);

                    dashboardWrapperModel.addPieChart(pieChartModel);

                    break;
                }
                case RoleName.HEAD_OF_ACADEMIC:
                case RoleName.ADMIN: {
                    //Get activated && deactivated user
                    pieChartModel = new PieChartModel();
                    pieChartModel.setTitle("Thống kê về người dùng theo trạng thái");

                    //Activated User
                    pieChartDataModel = new PieChartDataModel();
                    pieChartDataModel.setName("Số người dùng đang hoạt động");
                    pieChartDataModel.setValue(userRepository.countActiveUser());
                    pieChartModel.addData(pieChartDataModel);

                    //Deactivated User
                    pieChartDataModel = new PieChartDataModel();
                    pieChartDataModel.setName("Số người dùng ngưng hoạt động");
                    pieChartDataModel.setValue(userRepository.countDeactivatedUser());
                    pieChartModel.addData(pieChartDataModel);

                    dashboardWrapperModel.addPieChart(pieChartModel);

                    //Get activated && deactivated user
                    pieChartModel = new PieChartModel();
                    pieChartModel.setTitle("Thống kê về người dùng theo phân quyền");
                    List<Role> roleList = roleRepository.findAll();
                    for (Role r : roleList) {
                        pieChartDataModel = new PieChartDataModel();
                        pieChartDataModel.setName(r.getRoleName());
                        pieChartDataModel.setValue(userRepository.countAllByRoleByRoleIdId(r.getId()));
                        pieChartModel.addData(pieChartDataModel);
                    }
                    dashboardWrapperModel.addPieChart(pieChartModel);


                    //feedback by type
                    pieChartModel = new PieChartModel();
                    pieChartModel.setTitle("Thống kế về feedback theo loại");

                    List<Type> typeList = typeRepository.findAll();
                    for (Type t : typeList) {
                        pieChartDataModel = new PieChartDataModel();
                        pieChartDataModel.setName(t.getDescription());
                        pieChartDataModel.setValue(feedbackRepository.countAllByTypeByTypeIdId(t.getId()));
                        pieChartModel.addData(pieChartDataModel);
                    }

                    dashboardWrapperModel.addPieChart(pieChartModel);
                    break;
                }
                case RoleName.STAFF: {
                    pieChartModel = new PieChartModel();
                    pieChartModel.setTitle("Thống kế về feedback theo loại");

                    List<Type> typeList = typeRepository.findAll();
                    for (Type t : typeList) {
                        pieChartDataModel = new PieChartDataModel();
                        pieChartDataModel.setName(t.getDescription());
                        pieChartDataModel.setValue(feedbackRepository.countAllByTypeByTypeIdId(t.getId()));
                        pieChartModel.addData(pieChartDataModel);
                    }

                    dashboardWrapperModel.addPieChart(pieChartModel);

                    //Feedback for your department
                    pieChartModel = new PieChartModel();
                    pieChartModel.setTitle("Thống kê feedback cho phòng/ban của bạn");

                    //On going feedback
                    pieChartDataModel = new PieChartDataModel();
                    pieChartDataModel.setName("Feedback đang được diễn ra");
                    pieChartDataModel.setValue(feedbackRepository.countOnGoingFeedbackForDepartment(user.getDepartmentByDepartmentId().getId(), new Date(System.currentTimeMillis())));
                    pieChartModel.addData(pieChartDataModel);

                    //Finished feedback
                    pieChartDataModel = new PieChartDataModel();
                    pieChartDataModel.setName("Feedback đang được diễn ra");
                    pieChartDataModel.setValue(feedbackRepository.countFinishedFeedbackForDepartment(user.getDepartmentByDepartmentId().getId(), new Date(System.currentTimeMillis())));
                    pieChartModel.addData(pieChartDataModel);

                    dashboardWrapperModel.addPieChart(pieChartModel);
                    break;
                }
                case RoleName.STUDENT: {
                    //Do nothing, show only feedback-to-conduct list
                    break;
                }
            }
            dashboardWrapperModel.setFeedbackList(userFeedbackRepository.findNotConductedFeedbacksByUserId(user.getId()));
            return dashboardWrapperModel;
        }
    }


    private User getCurrentAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userRepository.findByUsername(username);
    }

}
