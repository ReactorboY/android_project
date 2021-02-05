package com.organicpy.retromvc.model;

/**
 * @author Mohd Hussain
 * @version 1.0
 * @since 03-02-2021
 */
public class FaqModel {
    private String question;
    private String answer;

    public FaqModel(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
