package com.concat.projetointegrador.exception.advice.sellerExceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorDTO {

    private final String error;
    private final String message;

}
