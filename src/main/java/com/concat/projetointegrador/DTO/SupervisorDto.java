package com.concat.projetointegrador.DTO;

import com.concat.projetointegrador.model.SupervisorModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupervisorDto {
    private String name;
    private String lastname;

    public SupervisorModel map(SupervisorDto supervisorDto) {
        return SupervisorModel.builder().name(supervisorDto.getName()).lastname(supervisorDto.getLastname()).build();
    }
    public SupervisorDto map(SupervisorModel supervisorModel) {
        return new SupervisorDto(supervisorModel.getName(), supervisorModel.getLastname());
    }
}
