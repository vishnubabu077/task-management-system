package com.task.logger.application.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.task.logger.error.model.TaskCustomError;
import com.task.logger.task.exception.CustomException;

import lombok.extern.log4j.Log4j2;

@RestControllerAdvice
@Log4j2
public class GlobalExceptionHandler  extends ResponseEntityExceptionHandler {


	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleGeneralException(Exception exception) {

		TaskCustomError error = new TaskCustomError();
		error.setMessage("Unexpected Error Occured, Contact Developer/ Sytem Admin");
		error.setDetails(exception.getLocalizedMessage());
		return new ResponseEntity<Object>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(CustomException.class)
	public final ResponseEntity<Object> handleCustomException(CustomException exception) {

		TaskCustomError error = new TaskCustomError();
		error.setMessage("Error Occured");
		error.setDetails(exception.getLocalizedMessage());
		log.error(exception.getMessage());
		return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
	}

}
