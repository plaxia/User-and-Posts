package com.rest.webservices.resftul_web_services.exception;

import java.time.LocalDateTime;

public class ErrorDetails {
	//timestamp
	private	LocalDateTime localDateTime;
	private String message;
	private String details;
	
	public ErrorDetails(LocalDateTime localDateTime,String message,String details) {
		
		this.localDateTime=localDateTime;
		this.details=details;
		this.message=message;
	}

	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}

	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}

}
