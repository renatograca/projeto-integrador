package com.concat.projetointegrador.exceptions.advice;

import com.concat.projetointegrador.exceptions.EntityNotFound;
import com.concat.projetointegrador.exceptions.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class InboundOrderControllerAdvice {

    @ExceptionHandler(EntityNotFound.class)
    public ResponseEntity entityNotFoundExceptionHandler(Exception e){
        ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity(errorMessage, HttpStatus.NOT_FOUND);
    }

}
