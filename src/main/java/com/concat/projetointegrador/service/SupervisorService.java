package com.concat.projetointegrador.service;

import com.concat.projetointegrador.model.SupervisorModel;
import com.concat.projetointegrador.repository.SupervisorRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupervisorService {
    private SupervisorRepository supervisorRepository;
    public SupervisorModel create(SupervisorModel supervisor) {
        if (supervisor.getName().length() < 3) throw new RuntimeException();
        return supervisorRepository.save(supervisor);
    }
}
