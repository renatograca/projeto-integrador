package com.concat.projetointegrador.exception.advice;

import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@AllArgsConstructor
@Builder
public class ErrorDTO {

    private String code;
    private String message;
    private LocalDateTime dateTime = LocalDateTime.now();

    public ErrorDTO(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
