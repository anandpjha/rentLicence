package com.namami.bo;

/**
 * 
 * @author Anand Jha
 * 
 */
public class EmailServiceRequest {

	private String mailTo;
	private String subject;
	private StringBuilder message;
	
	
	public String getMailTo() {
		return mailTo;
	}
	public void setMailTo(String mailTo) {
		this.mailTo = mailTo;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public StringBuilder getMessage() {
		return message;
	}
	public void setMessage(StringBuilder message) {
		this.message = message;
	}
	

}
