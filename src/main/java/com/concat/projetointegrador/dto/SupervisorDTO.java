package com.concat.projetointegrador.dto;

import com.concat.projetointegrador.model.Supervisor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupervisorDTO {
    private String name;
    private String lastname;

    public static Supervisor map(SupervisorDTO supervisorDto) {
        return Supervisor.builder().name(supervisorDto.getName()).lastName(supervisorDto.getLastname()).build();
    }
    public static SupervisorDTO map(Supervisor supervisor) {
        return new SupervisorDTO(supervisor.getName(), supervisor.getLastName());
    }
}
