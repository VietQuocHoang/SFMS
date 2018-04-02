package com.sample.sfms.model.answer;


import java.util.ArrayList;
import java.util.List;

public class ConductAnswerWrapper {
    private int feedbackId;
    private List<ConductAnswer> answers;

    public ConductAnswerWrapper() {
        answers = new ArrayList<>();
    }

    public int getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }

    public List<ConductAnswer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<ConductAnswer> answers) {
        this.answers = answers;
    }
}
