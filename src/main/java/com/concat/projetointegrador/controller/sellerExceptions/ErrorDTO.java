package com.concat.projetointegrador.controller.sellerExceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorDTO {

    private final String error;
    private final String message;

}
