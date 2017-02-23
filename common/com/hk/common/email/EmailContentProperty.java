package com.hk.common.email;

import javax.mail.internet.InternetAddress;

public class EmailContentProperty {
	
	private String sender;
	private InternetAddress[] recipients;
	private String subject;
	private String text;
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public InternetAddress[] getRecipients() {
		return recipients;
	}
	public void setRecipients(InternetAddress[] recipients) {
		this.recipients = recipients;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	

}
