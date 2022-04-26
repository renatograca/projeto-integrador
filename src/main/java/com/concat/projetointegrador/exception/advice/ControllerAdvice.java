package com.concat.projetointegrador.exception.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ControllerAdvice {

    private final String NOT_FOUND = String.valueOf(HttpStatus.NOT_FOUND.value());

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorDTO> objectNotFound(Exception e) {
        ErrorDTO errorDTO = new ErrorDTO(NOT_FOUND, e.getMessage());
        return new ResponseEntity<>(errorDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> handleValidationExceptions(MethodArgumentNotValidException e) {
        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        List<String> results = new ArrayList<>();
        errors.forEach(x -> results.add(x.getDefaultMessage()));
        ErrorDTO errorDTO = new ErrorDTO(HttpStatus.BAD_REQUEST.toString(), results.toString());
        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }

}
