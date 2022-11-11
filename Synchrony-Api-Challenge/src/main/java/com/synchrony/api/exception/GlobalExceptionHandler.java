package com.synchrony.api.exception;



import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(DataNotFoundException.class)
	public ResponseEntity<?> resourceNotFoundHandler(DataNotFoundException dataNotFoundException){
		
		ErrorDetails errorDetails= new ErrorDetails(new Date() , dataNotFoundException.getMessage() , HttpStatus.NOT_FOUND.name());
		
		return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(NotAuthorizedException.class)
	public ResponseEntity<?> InputException(NotAuthorizedException exception, WebRequest request){
		ErrorDetails errorDetails = 
				new ErrorDetails(new Date(), exception.getMessage(), HttpStatus.UNAUTHORIZED.name());
		return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
	}

	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> globalExceptionHandling(Exception exception, WebRequest request){
		ErrorDetails errorDetails = 
				new ErrorDetails(new Date(), exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.name());
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
