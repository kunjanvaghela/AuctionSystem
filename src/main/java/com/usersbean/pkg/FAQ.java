package com.usersbean.pkg;

//import java.sql.Date;

//import java.io.Serializable;

public class FAQ extends EndUsers {
    private static final long serialVersionUID = 1L;
    private Long faqId;
    private String question;
	private String answer;
	private String statusFAQ;
	
	public static long getSerialversionuid() {
		return serialVersionUID;
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

	public String getStatusFAQ() {
		return statusFAQ;
	}

	public void setStatusFAQ(String statusFAQ) {
		this.statusFAQ = statusFAQ;
	}

	public Long getFaqId() {
		return faqId;
	}

	public void setFaqId(Long faqId) {
		this.faqId = faqId;
	}

}