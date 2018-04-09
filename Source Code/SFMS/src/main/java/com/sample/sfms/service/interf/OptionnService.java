package com.sample.sfms.service.interf;

import com.sample.sfms.entity.Optionn;
import com.sample.sfms.model.option.OptionCreateModel;
import com.sample.sfms.model.option.OptionUpdateModel;

import java.util.List;

public interface OptionnService {
    int add(OptionCreateModel model) throws Exception;
    void update(OptionUpdateModel model) throws Exception;
    void remove(int id) throws Exception;
    public List<Optionn> findByQuestionId(int questionId);
}
