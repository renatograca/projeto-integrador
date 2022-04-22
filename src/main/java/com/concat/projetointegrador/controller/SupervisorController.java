package com.concat.projetointegrador.controller;

import com.concat.projetointegrador.DTO.SupervisorDto;
import com.concat.projetointegrador.model.SupervisorModel;
import com.concat.projetointegrador.service.SupervisorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SupervisorController {
    @Autowired
    private SupervisorService supervisorService;

    @PostMapping("/supervisor")
    public ResponseEntity<SupervisorDto> create(SupervisorDto supervisorDto) {
        SupervisorModel supervisor = SupervisorDto.map(supervisorDto);
        SupervisorModel newSupervisor = supervisorService.create(supervisor);
        return ResponseEntity.created().body(newSupervisor);
    }
}
