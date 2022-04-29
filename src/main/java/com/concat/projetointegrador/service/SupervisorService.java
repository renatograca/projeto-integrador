package com.concat.projetointegrador.service;

import com.concat.projetointegrador.dto.SupervisorDTO;
import com.concat.projetointegrador.model.Supervisor;
import com.concat.projetointegrador.repository.SupervisorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SupervisorService {
    private SupervisorRepository supervisorRepository;

    public Supervisor findById(Long id) {
        return supervisorRepository.findById(id).orElseThrow(() -> new RuntimeException("Não foi encontrado!"));
    }

}
