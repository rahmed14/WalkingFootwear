package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CustomerAppException extends Exception {

	private static final long serialVersionUID = 1L;

	public CustomerAppException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public CustomerAppException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
}
