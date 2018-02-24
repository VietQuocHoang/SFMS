package com.sample.sfms.service.impl;

import com.sample.sfms.entity.*;
import com.sample.sfms.repository.QuestionRepository;
import com.sample.sfms.service.interf.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.UnexpectedRollbackException;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Binh Nguyen on 24-Feb-18.
 */
public class QuestionServiceImpl implements QuestionService {

    static Logger logger = Logger.getLogger(RoleServiceImpl.class.getName());

    @Autowired
    private QuestionRepository questionRepo;

 /*   @Override
    public ResponseEntity<Question> saveQuestion(String type, String suggestion, byte isRequired, String content) {
        ResponseEntity<Question> response;
        try {
            Question question = new Question();
            question.setType(type);
            question.setSuggestion(suggestion);
            question.setIsRequied(isRequired);
            question.setQuestionContent(content);

            List<Optionn> options = new ArrayList<>();
            for (Optionn o : options) {

            }
            response = new ResponseEntity<>(question, HttpStatus.OK);
            return response;
        } catch (UnexpectedRollbackException e){
            logger.log(Level.FINE, e.toString());
            response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            return response;
        }
    }*/

}
