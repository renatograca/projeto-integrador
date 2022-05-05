package com.concat.projetointegrador.service;

import com.concat.projetointegrador.model.Supervisor;
import com.concat.projetointegrador.repository.SupervisorRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SupervisorService {

    private SupervisorRepository supervisorRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     * search supervisor by id
     * @param id Long - supervisor id
     * @return returns a supervisor if one exists
     */
    public Supervisor findById(Long id) {
        return supervisorRepository.findById(id).orElseThrow(() -> new RuntimeException("Não foi encontrado!"));
    }

    /**
     * create a new supervisor
     * @param supervisor - a object supervisor
     * @return returns the created supervisor
     */
    public Supervisor create(Supervisor supervisor) {
        supervisor.setPassword(passwordEncoder.encode(supervisor.getPassword()));
        return supervisorRepository.save(supervisor);
    }
}