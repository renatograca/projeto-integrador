package com.concat.projetointegrador.dto;

import com.concat.projetointegrador.model.Sector;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SectorDTO {
    @NotNull
    private Integer capacity;

}
