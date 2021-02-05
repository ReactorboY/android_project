package com.organicpy.rxretromvp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author Mohd Hussain
 * @version 1.0
 * @since 04-02-2021
 */
public class FaqModel {
    @SerializedName("faq")
    @Expose
    private List<Faq> faq = null;
    public List<Faq> getFaq() {
        return faq;
    }

    public void setFaq(List<Faq> faq) {
        this.faq = faq;
    }

    public class Faq {
        @SerializedName("faqId")
        @Expose
        private int faqId;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("question")
        @Expose
        private String question;
        @SerializedName("answer")
        @Expose
        private String answer;

        public int getFaqId() {
            return faqId;
        }

        public void setFaqId(Integer faqId) {
            this.faqId = faqId;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
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
}
