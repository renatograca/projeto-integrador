package com.concat.projetointegrador.exception.advice.sellerExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class SellerExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> handleValidationExceptions(MethodArgumentNotValidException e) {
        List<ObjectError> error = e.getBindingResult().getAllErrors();
        List<String> results = new ArrayList<>();
        error.forEach(x -> results.add(x.getDefaultMessage()));
        ErrorDTO errorDTO = new ErrorDTO(HttpStatus.BAD_REQUEST.toString(), results.toString());
        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<String> noSeller(NoSellerException ex) {

        return ResponseEntity.badRequest().body(ex.getMessage());

    }

}
