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
public class EntityExcpetion {

	public EntityExcpetion() {
		System.out.println("Entity exc constructor");
	}

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<Object> runtimeExceptionHandler(EntityNotFoundException ex){
		System.out.println("entity not found exception handler");
		Map<String, Object> map = new HashMap<>();

		map.put(AppConstants.STATUS, Status.FAILURE);
		map.put("message",ex.getMessage());
		
		return ResponseEntity.badRequest().body(map);

	}
	@ExceptionHandler(EntityExistsException.class)
	public ResponseEntity<Object> runtimeExceptionHandlerIfExists(EntityExistsException ex){
		System.out.println("entity exists exception handler");
		Map<String, Object> map = new HashMap<>();

		map.put(AppConstants.STATUS, Status.FAILURE);
		map.put("error",ex.getMessage());
		return ResponseEntity.badRequest().body(map);

	}
	

}
