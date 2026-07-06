package com.aurgroup.licoreria.Controllers;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
/**
 * GlobalExceptionHandler
 */
import org.springframework.http.ResponseEntity;
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<Map<String, String>> handleDataIntegrity(DataIntegrityViolationException ex) {
		Map<String, String> error = new HashMap<>();
		error.put("Error", "DATA_INTEGRITY_VIOLATION");
		error.put("Message", ex.getMessage());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
	}
}
