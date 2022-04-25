package com.concat.projetointegrador.dto;

import com.concat.projetointegrador.model.SupervisorModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupervisorDTO {
    private String name;
    private String lastname;

    public static SupervisorModel map(SupervisorDTO supervisorDto) {
        return SupervisorModel.builder().name(supervisorDto.getName()).lastname(supervisorDto.getLastname()).build();
    }
    public static SupervisorDTO map(SupervisorModel supervisorModel) {
        return new SupervisorDTO(supervisorModel.getName(), supervisorModel.getLastname());
    }
}
