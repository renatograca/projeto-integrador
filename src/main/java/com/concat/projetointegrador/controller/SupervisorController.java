package com.concat.projetointegrador.controller;

import com.concat.projetointegrador.DTO.SupervisorDto;
import com.concat.projetointegrador.model.SupervisorModel;
import com.concat.projetointegrador.service.SupervisorService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
public class SupervisorController {
    @Autowired
    private SupervisorService supervisorService;

    @PostMapping("/supervisor")
    public ResponseEntity<SupervisorDto> create(@RequestBody SupervisorDto supervisorDto, UriComponentsBuilder uriBuilder) {
        SupervisorModel supervisor = SupervisorModel.builder().name(supervisorDto.getName()).lastname(supervisorDto.getLastname()).build();
        SupervisorModel newSupervisor = supervisorService.create(supervisor);
        SupervisorDto supervisorReturn = SupervisorDto.map(newSupervisor);
        URI uri = uriBuilder
                .path("/supervisor/{id}")
                .buildAndExpand(newSupervisor.getId())
                .toUri();
        return ResponseEntity.created(uri).body(supervisorReturn);
    }

    @GetMapping("/supervisor")
    public ResponseEntity<List<SupervisorDto>> list() {
        List<SupervisorModel> supervisorModels = supervisorService.list();
        List<SupervisorDto> supervisorDtos = new ArrayList<>();

        supervisorModels.forEach(e -> supervisorDtos.add(SupervisorDto.map(e)));
        return ResponseEntity.ok(supervisorDtos);
    }
}
