package com.sample.sfms.service.interf;
import com.sample.sfms.entity.Feedback;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FeedbackService {

    List<Feedback> getListTemplate(boolean isTemplate);

}

