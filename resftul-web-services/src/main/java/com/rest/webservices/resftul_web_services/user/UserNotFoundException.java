package com.rest.webservices.resftul_web_services.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {
		
	public UserNotFoundException(String message) {
		
		super(message);
		
	}

}