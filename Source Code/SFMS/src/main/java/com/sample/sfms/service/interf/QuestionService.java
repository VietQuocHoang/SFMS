package com.sample.sfms.service.interf;

import com.sample.sfms.entity.Clazz;
import com.sample.sfms.entity.Question;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * Created by MyPC on 21/02/2018.
 */

public interface QuestionService {

    public ResponseEntity<Question> saveQuestion(String type, String suggestion, byte isRequired, String content);
}
