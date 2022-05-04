package com.concat.projetointegrador.dto;

import com.concat.projetointegrador.model.Supervisor;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SupervisorDTO {

    private Long id;
    private String name;
    private String lastName;
    private String username;
    private Long cpf;

    public static SupervisorDTO map(Supervisor supervisor) {
        return SupervisorDTO.builder()
                .id(supervisor.getId())
                .name(supervisor.getName())
                .lastName(supervisor.getLastName())
                .username(supervisor.getUsername())
                .cpf(supervisor.getCpf())
                .build();
    }
}