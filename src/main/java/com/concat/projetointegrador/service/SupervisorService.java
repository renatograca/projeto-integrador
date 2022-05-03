package com.concat.projetointegrador.service;

import com.concat.projetointegrador.model.Supervisor;
import com.concat.projetointegrador.repository.SupervisorRepository;
import org.springframework.stereotype.Service;

@Service
public class SupervisorService {
    private SupervisorRepository supervisorRepository;

    public SupervisorService(SupervisorRepository supervisorRepository) {
        this.supervisorRepository = supervisorRepository;
    }

    public Supervisor findById(Long id) {
        return supervisorRepository.findById(id).orElseThrow(() -> new RuntimeException("Não foi encontrado!"));
    }

}
