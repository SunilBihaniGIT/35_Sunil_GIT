package com.javaexpress.service.exception;

public class ResourceNotFoundException extends RuntimeException {
	
	public ResourceNotFoundException(String message) {
	  super(message);
	}
}
