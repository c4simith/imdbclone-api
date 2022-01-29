package com.app.imdb.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

/**
 * Exception class to handle scenario when no title returned based on user input. 
 */
@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="No such title")
public class TitleNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -8286945873791981822L;

	public TitleNotFoundException(String message) {
		super(message);
	}
}
