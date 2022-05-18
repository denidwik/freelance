package com.project.freelance.exception;

import org.springframework.security.core.AuthenticationException;

public class NoDataFoundException extends AuthenticationException {

	public NoDataFoundException() {
		super("No data found");
	}
	
	public NoDataFoundException(String msg) {
		super(msg);
	}
}
