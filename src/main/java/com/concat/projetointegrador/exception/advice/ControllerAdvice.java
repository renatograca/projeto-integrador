package com.concat.projetointegrador.exception.advice;

import com.concat.projetointegrador.exception.CategoryNotFoundException;
import com.concat.projetointegrador.exception.ErrorDTO;
import org.apache.commons.lang3.exception.ExceptionUtils;
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
        e.printStackTrace();
        ErrorDTO errorDTO = new ErrorDTO(NOT_FOUND, e.getMessage());
        return new ResponseEntity<>(errorDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ErrorDTO> illegalArgumentExceptionHandl(Exception e) {
        e.printStackTrace();
        ErrorDTO errorDTO = new ErrorDTO(NOT_FOUND, ExceptionUtils.getRootCause(e).getMessage().split(":" )[0]);
        return new ResponseEntity<>(errorDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> handleValidationExceptions(MethodArgumentNotValidException e) {
        e.printStackTrace();
        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        List<String> results = new ArrayList<>();
        errors.forEach(x -> results.add(x.getDefaultMessage()));
        ErrorDTO errorDTO = new ErrorDTO(HttpStatus.BAD_REQUEST.toString(), results.toString());
        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }

}
