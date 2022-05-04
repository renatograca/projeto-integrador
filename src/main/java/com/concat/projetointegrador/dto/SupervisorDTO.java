package com.concat.projetointegrador.dto;

import com.concat.projetointegrador.model.Supervisor;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupervisorDTO {
    private String name;
    private String lastName;
    private String userName;
    @JsonIgnoreProperties
    private String password;

    public static Supervisor map(SupervisorDTO supervisorDto) {
        return Supervisor.builder()
                .name(supervisorDto.getName())
                .lastName(supervisorDto.getLastName())
                .username(supervisorDto.getUserName())
                .password(supervisorDto.getPassword())
                .build();
    }
    public static SupervisorDTO map(Supervisor supervisor) {
        return new SupervisorDTO(supervisor.getName(), supervisor.getLastName(), supervisor.getUsername(), supervisor.getPassword());
    }
}
