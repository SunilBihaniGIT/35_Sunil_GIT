package com.javaexpress.service.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ErrorAPI handleException(ResourceNotFoundException ex) {
		var errorApi = new ErrorAPI();
		errorApi.setDetails(ex.getMessage());
		errorApi.setTitle("Resource Not Found in DB");
		errorApi.setLocalDateTime(LocalDateTime.now());
		errorApi.setStatus(HttpStatus.BAD_REQUEST.getReasonPhrase());
		errorApi.setStatusCode(HttpStatus.BAD_REQUEST.value());
		
		return errorApi;		
	}
	
	/* Old Way Implementation
	 
	@ExceptionHandler(ResourceNotFoundException.class)	
	public ResponseEntity<ErrorAPI> handleException1(ResourceNotFoundException ex) {
		var errorApi = new ErrorAPI();
		errorApi.setDetails(ex.getMessage());
		errorApi.setTitle("Resource Not Found in DB");
		errorApi.setLocalDateTime(LocalDateTime.now());
		errorApi.setStatus(HttpStatus.BAD_REQUEST.getReasonPhrase());
		errorApi.setStatusCode(HttpStatus.BAD_REQUEST.value());
		
		return new ResponseEntity<>(errorApi ,HttpStatus.BAD_REQUEST);		
	}
	*/
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorAPI handleException(Exception ex) {
		var errorApi = new ErrorAPI();		
		errorApi.setDetails(ex.getMessage());
		errorApi.setTitle("Something went wrong");
		errorApi.setLocalDateTime(LocalDateTime.now());
		errorApi.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
		errorApi.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		
		return errorApi;		
	}
}
