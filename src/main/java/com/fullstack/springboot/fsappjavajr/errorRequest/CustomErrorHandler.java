package com.fullstack.springboot.fsappjavajr.errorRequest;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomErrorHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorDetails> handleServerException(Exception ex, WebRequest request) throws Exception {
		LocalDateTime date = LocalDateTime.now();
		ErrorDetails errorDetails = new ErrorDetails(date, ex.getMessage(), request.getDescription(false));
		ResponseEntity<ErrorDetails> internalServerError = new ResponseEntity<ErrorDetails>(errorDetails,
				HttpStatus.INTERNAL_SERVER_ERROR);
		return internalServerError;
	}

//	@ExceptionHandler(UserNotFoundException.class)
//	public final ResponseEntity<ErrorDetails> handleUserNotFoundException(Exception ex, WebRequest request)
//			throws Exception {
//		LocalDateTime date = LocalDateTime.now();
//		ErrorDetails errorDetails = new ErrorDetails(date, ex.getMessage(), request.getDescription(false));
//		ResponseEntity<ErrorDetails> notFoundError = new ResponseEntity(errorDetails, HttpStatus.NOT_FOUND);
//		return notFoundError;
//	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		LocalDateTime date = LocalDateTime.now();
		ErrorDetails errorDetails = new ErrorDetails(date, ex.getFieldError().getDefaultMessage(),
				request.getDescription(false));
		ResponseEntity badRequestError = new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
		return badRequestError;
	}
}
