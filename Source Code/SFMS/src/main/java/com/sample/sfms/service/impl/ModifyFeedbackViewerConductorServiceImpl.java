package com.sample.sfms.service.impl;

import com.sample.sfms.entity.*;
import com.sample.sfms.model.FeedbackDetailsModel;
import com.sample.sfms.repository.*;
import com.sample.sfms.service.interf.ModifyFeedbackViewConductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by MyPC on 23/02/2018.
 */
@Service("ModifyFeedbackViewConductService")
public class ModifyFeedbackViewerConductorServiceImpl implements ModifyFeedbackViewConductService {

    static Logger logger = Logger.getLogger(RoleServiceImpl.class.getName());

    @Autowired
    private FeedbackRepository feedbackRepo;

    @Autowired
    private MajorRepository majorRepo;

    @Autowired
    private CourseRepository courseRepo;

    @Autowired
    private ClazzRepository clazzRepo;

    @Autowired
    private DepartmentRepository departmentRepo;

    @Autowired
    private TypeRepository typeRepo;

    @Autowired
    private UserFilteringRepository userfilterRepo;

    @Autowired
    private StudentClazzRepository studentClazzRepo;

    @Autowired
    private UserRepository userRepo;

    @Override
    public FeedbackDetailsModel addConductors(FeedbackDetailsModel model, List<User> conductorIds) {
        FeedbackDetailsModel feedbackmodel = null;
        try {
            List<User> conductors = model.getConductors();
            conductors.addAll(conductorIds);
            feedbackmodel.setConductors(conductors);
        } catch (Exception e){
            e.printStackTrace();
        }
        return feedbackmodel;
    }

    @Override
    public FeedbackDetailsModel addViewers(FeedbackDetailsModel model, List<User> viewerIds) {
        FeedbackDetailsModel feedbackmodel = null;
        try {
            List<User> viewers = model.getReportviewers();
            viewers.addAll(viewerIds);
            feedbackmodel.setConductors(viewers);
        } catch (Exception e){
            e.printStackTrace();
        }
        return feedbackmodel;
    }

    @Override
    public FeedbackDetailsModel removeConductors(FeedbackDetailsModel model, List<User> conductorIds) {
        FeedbackDetailsModel feedbackmodel = null;
        try {
            List<User> conductors = model.getConductors();
            for (User user : conductorIds){
                if (conductors.contains(user)) {
                    conductors.remove(user);
                }
            }
            feedbackmodel.setConductors(conductors);
        } catch (Exception e){
            e.printStackTrace();
        }
        return feedbackmodel;
    }

    @Override
    public FeedbackDetailsModel removeViewers(FeedbackDetailsModel model, List<User> viewerIds) {
        FeedbackDetailsModel feedbackmodel = null;
        try {
            List<User> viewers = model.getReportviewers();
            for (User user : viewerIds){
                if (viewers.contains(user)) {
                    viewers.remove(user);
                }
            }
            feedbackmodel.setConductors(viewers);
        } catch (Exception e){
            e.printStackTrace();
        }
        return feedbackmodel;
    }
}
