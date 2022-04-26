package com.concat.projetointegrador.service;

import com.concat.projetointegrador.dto.SupervisorDTO;
import com.concat.projetointegrador.model.Supervisor;
import com.concat.projetointegrador.repository.SupervisorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupervisorService {
    private SupervisorRepository supervisorRepository;

    public SupervisorService(SupervisorRepository supervisorRepository) {
        super();
        this.supervisorRepository = supervisorRepository;
    }

    public Supervisor create(Supervisor supervisor) {
        if (supervisor.getName().length() < 3) throw new RuntimeException();
        return supervisorRepository.save(supervisor);
    }

    public List<Supervisor> findAll() {
        return supervisorRepository.findAll();
    }

    public Supervisor findById(Long id) {
        return supervisorRepository.findById(id).orElseThrow(() -> new RuntimeException("Não foi encontrado!"));
    }

    public Supervisor update(Long id, SupervisorDTO supervisor) {
        Supervisor supervisorModel = supervisorRepository.findById(id).orElse(new Supervisor());
        supervisorModel.setName(supervisor.getName());
        supervisorModel.setLastname(supervisor.getLastname());
        return supervisorRepository.save(supervisorModel);
    }
}
