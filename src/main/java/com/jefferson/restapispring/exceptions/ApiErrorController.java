package com.jefferson.restapispring.exceptions;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Arrays;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ApiErrorController extends ResponseEntityExceptionHandler{
	
	@Override
	@ExceptionHandler({Exception.class, RuntimeException.class, Throwable.class, IllegalArgumentException.class})
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		ApiError apiError = new ApiError();
		apiError.setCodigo(status.value());
		apiError.setMensagem(status.getReasonPhrase());
		apiError.setErrors(Arrays.asList(ex.getMessage()));
		apiError.setTimestamp(Timestamp.from(Instant.now()));
		return new ResponseEntity<>(apiError, headers, status);
	}
	
	
	@ExceptionHandler({DataIntegrityViolationException.class, ConstraintViolationException.class, SQLException.class})
	protected ResponseEntity<Object> handleExceptionDataIntegrity(Exception ex) {
		ApiError apiError = new ApiError();
		apiError.setCodigo(HttpStatus.INTERNAL_SERVER_ERROR.value());
		apiError.setMensagem(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
		apiError.setErrors(Arrays.asList(ex.getMessage()));
		apiError.setTimestamp(Timestamp.from(Instant.now()));
		return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
