package com.concat.projetointegrador.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SectorDTO {
    @NotNull
    private Integer capacity;
}
