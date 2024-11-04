package com.example.feignclient.mobile.globalexceptionhandle;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandle {

	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<ExceptionMessage> IdNotFoundException(IdNotFoundException idNotFoundException,
			WebRequest webRequest) {
		ExceptionMessage message = new ExceptionMessage(idNotFoundException.getMessage(),
				webRequest.getDescription(false), new Date());

		return new ResponseEntity<ExceptionMessage>(message, HttpStatus.NOT_FOUND);
	}

}
