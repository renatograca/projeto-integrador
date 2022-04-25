package com.concat.projetointegrador.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ErrorDTO {

	private String code;
	private String menssage;
	private LocalDateTime dateTime = LocalDateTime.now();
	
	public ErrorDTO(String code, String message) {
		this.code = code;
		this.menssage = message;
				
	}
	
}