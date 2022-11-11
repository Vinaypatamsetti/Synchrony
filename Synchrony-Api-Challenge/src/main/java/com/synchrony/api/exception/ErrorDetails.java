package com.synchrony.api.exception;

import java.util.Date;

public class ErrorDetails {

	private Date timestamp;
	private String message;
	private String responseStatus;
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getResponseStatus() {
		return responseStatus;
	}
	public void setResponseStatus(String responseStatus) {
		this.responseStatus = responseStatus;
	}
	
	public ErrorDetails(Date timestamp, String message, String responseStatus) {
		this.timestamp = timestamp;
		this.message = message;
		this.responseStatus = responseStatus;
	}
	public ErrorDetails() {
		
	}
	
	
}
