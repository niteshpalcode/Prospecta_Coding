package com.prospecta.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalException {

	@ExceptionHandler(EntryNotFoundException.class)
	public ResponseEntity<MyErrorDetails> myENFEHandler(EntryNotFoundException en,WebRequest we){
		MyErrorDetails error=new MyErrorDetails();
		
		error.setTimeStamp(LocalDateTime.now());
		error.setMessage(en.getMessage());
		error.setDescription(we.getDescription(false));
		return new ResponseEntity<MyErrorDetails>(error,HttpStatus.BAD_REQUEST);
		
	}
	
	
}
