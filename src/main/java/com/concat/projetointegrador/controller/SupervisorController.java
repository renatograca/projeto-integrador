package com.concat.projetointegrador.controller;

import com.concat.projetointegrador.dto.SupervisorDTO;
import com.concat.projetointegrador.model.Supervisor;
import com.concat.projetointegrador.service.SupervisorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/supervisor")
public class SupervisorController {

    @Autowired
    private SupervisorService supervisorService;

    /**
     * create a new supervisor
     * @param supervisor - object supervisor
     * @return a supervisor DTO
     */
    @PostMapping
    public ResponseEntity<SupervisorDTO> create(@RequestBody Supervisor supervisor) {
        SupervisorDTO newSupervisor = SupervisorDTO.map(supervisorService.create(supervisor));
        return new ResponseEntity<>(newSupervisor, HttpStatus.CREATED);
    }
}