package com.sample.sfms.service.interf;

import com.sample.sfms.entity.Clazz;
import com.sample.sfms.entity.Semester;
import com.sample.sfms.model.FeedbackReportModel;
import com.sample.sfms.model.feedback.FeedbackTargetWrapper;
import com.sample.sfms.model.report.reportList.ReportLecturerCourse;
import com.sample.sfms.model.report.reportSemester.ReportSemesterModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * Created by MyPC on 21/02/2018.
 */

public interface ReportService {

    List<Clazz> loadListClassByCourseLecturerSemester (int type, int userId, int courseId, int semesterId);

    List<Object> loadListReport (String type);

    List<FeedbackReportModel> loadReportDetail(int courseId, int userId, int type, int semesterId);

    FeedbackTargetWrapper loadListFeedbackTargetWrapper();

    ReportSemesterModel getReportSemesterDetail(int typeId, int semId, int targetId);

    List<Clazz> findClazzByLectureId(int lectureId);

    List<Semester> findAllSemester();

    List<ReportLecturerCourse> findAllCourseCorrespondingToCurrentLecturer();

}
