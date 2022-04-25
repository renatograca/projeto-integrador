package com.concat.projetointegrador.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class ErrorMessage {
    private String message;
    private Integer statusCode;

    public ErrorMessage(String message, Integer statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }
}
