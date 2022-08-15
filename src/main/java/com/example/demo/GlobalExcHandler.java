package com.example.demo;

import java.util.Set;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class GlobalExcHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Object> notFoundHandler(Exception ex,WebRequest request){
		
		ExceptionResponse exr = new ExceptionResponse("whoever you searched for could not be found");
		return new ResponseEntity(exr,HttpStatus.NOT_FOUND);
	}
	

	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<Object> nullHandler(Exception ex,WebRequest request){
		
		ExceptionResponse exr = new ExceptionResponse("Somethings wrong internally");
		return new ResponseEntity(exr,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
			HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		ExceptionResponse exr = new ExceptionResponse(
"Not Valid Method");
		return new ResponseEntity(exr,HttpStatus.METHOD_NOT_ALLOWED);
	}
	
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionResponse exr = new ExceptionResponse(ex.getBindingResult().toString());
		return new ResponseEntity(exr,HttpStatus.BAD_REQUEST);
	}
	
	

}
