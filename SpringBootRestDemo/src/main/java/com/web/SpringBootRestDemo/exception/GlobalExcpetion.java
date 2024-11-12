package com.web.SpringBootRestDemo.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.web.SpringBootRestDemo.utility.AppConstants;
import com.web.SpringBootRestDemo.utility.Status;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@ControllerAdvice
public class GlobalExcpetion {

	public GlobalExcpetion() {
		System.out.println("global exc constructor");
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<Object> runtimeExceptionHandler1(RuntimeException ex){
		System.out.println("runtime exception handler");
		Map<String, Object> map = new HashMap<>();

		map.put(AppConstants.STATUS, Status.FAILURE);
		map.put("error",ex.getMessage());
		return ResponseEntity.badRequest().body(map);

	}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> exceptionHandler1(Exception ex){
		System.out.println(" exception handler");
		Map<String, Object> map = new HashMap<>();

		map.put(AppConstants.STATUS, Status.FAILURE);
		map.put("error",ex.getMessage());
		return ResponseEntity.badRequest().body(map);

	}

}
