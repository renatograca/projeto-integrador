package com.concat.projetointegrador.service;

import com.concat.projetointegrador.model.Supervisor;
import com.concat.projetointegrador.repository.SupervisorRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SupervisorService {
    private SupervisorRepository supervisorRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public SupervisorService(SupervisorRepository supervisorRepository, BCryptPasswordEncoder passwordEncoder) {
        this.supervisorRepository = supervisorRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Supervisor findById(Long id) {
        return supervisorRepository.findById(id).orElseThrow(() -> new RuntimeException("Não foi encontrado!"));
    }

    public Supervisor create(Supervisor supervisor) {
        supervisor.setPassword(passwordEncoder.encode(supervisor.getPassword()));
        return supervisorRepository.save(supervisor);
    }
}
