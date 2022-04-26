package com.concat.projetointegrador.exception.advice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.concat.projetointegrador.exception.EntityNotFound;
import com.concat.projetointegrador.exception.ErrorDTO;

@RestControllerAdvice
public class InboundOrderControllerAdvice {

	@ExceptionHandler(EntityNotFound.class)
	public ResponseEntity<ErrorDTO> entityNotFoundExceptionHandler(Exception e) {
		ErrorDTO errorMessage = new ErrorDTO(e.getMessage(), e.getLocalizedMessage());
		return new ResponseEntity<ErrorDTO>(errorMessage, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorDTO> handleValidationExceptions(MethodArgumentNotValidException e) {
		List<ObjectError> erros = e.getBindingResult().getAllErrors();
		List<String> results = new ArrayList<String>();
		erros.forEach(x -> results.add(x.getDefaultMessage()));
		ErrorDTO errorDTO = new ErrorDTO(HttpStatus.BAD_REQUEST.toString(), results.toString());
		return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
	}

}
