package com.concat.projetointegrador.service;

import com.concat.projetointegrador.dto.SupervisorDTO;
import com.concat.projetointegrador.model.Supervisor;
import com.concat.projetointegrador.repository.SupervisorRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SupervisorService {
    private SupervisorRepository supervisorRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public Supervisor findById(Long id) {
        return supervisorRepository.findById(id).orElseThrow(() -> new RuntimeException("Não foi encontrado!"));
    }

    public Supervisor create(Supervisor supervisor) {
        supervisor.setPassword(passwordEncoder.encode(supervisor.getPassword()));
        return supervisorRepository.save(supervisor);
    }
}
