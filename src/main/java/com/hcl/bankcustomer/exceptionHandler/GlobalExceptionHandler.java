package com.hcl.bankcustomer.exceptionHandler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.hcl.bankcustomer.exception.CustomerDataNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(CustomerDataNotFoundException.class)
	public ResponseEntity<ErrorResponse> allExceptionHandler(Exception exception,WebRequest request)
	{
		ErrorResponse errorResponse =  new ErrorResponse(new Date(), exception.getMessage(), request.getDescription(false));
		return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.NOT_FOUND);
	}
}
