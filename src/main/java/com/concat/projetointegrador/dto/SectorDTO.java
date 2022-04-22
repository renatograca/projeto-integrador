package com.concat.projetointegrador.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class SectorDTO {

    @NotBlank
    private Long id;
    @NotBlank
    private Integer capacity;
}
