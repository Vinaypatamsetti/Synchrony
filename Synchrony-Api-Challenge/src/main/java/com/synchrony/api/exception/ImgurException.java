package com.synchrony.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class ImgurException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public ImgurException(String message) {
		super(message);
	}

}
