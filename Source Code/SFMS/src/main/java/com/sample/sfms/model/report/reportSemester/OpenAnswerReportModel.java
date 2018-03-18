package com.sample.sfms.model.report.reportSemester;

import java.sql.Timestamp;

public class OpenAnswerReportModel {

    private Timestamp conductDate;

    private String answerContent;


    public String getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }

    public Timestamp getConductDate() {
        return conductDate;
    }

    public void setConductDate(Timestamp conductDate) {
        this.conductDate = conductDate;
    }

}

