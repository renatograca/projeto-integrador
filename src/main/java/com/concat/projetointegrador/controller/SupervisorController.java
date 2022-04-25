package com.concat.projetointegrador.controller;

import com.concat.projetointegrador.DTO.SupervisorDto;
import com.concat.projetointegrador.model.SupervisorModel;
import com.concat.projetointegrador.service.SupervisorService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @PostMapping
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

    @GetMapping
    public ResponseEntity<List<SupervisorDto>> list() {
        List<SupervisorModel> supervisorModels = supervisorService.list();
        List<SupervisorDto> supervisorDtos = new ArrayList<>();

        supervisorModels.forEach(e -> supervisorDtos.add(SupervisorDto.map(e)));
        return ResponseEntity.ok(supervisorDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupervisorDto> getSupervisor(@PathVariable Long id) {
        SupervisorDto supervisorDto = SupervisorDto.map(supervisorService.getSupervisor(id));
        return ResponseEntity.ok(supervisorDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SupervisorDto> update(@PathVariable Long id, @RequestBody SupervisorDto supervisor, UriComponentsBuilder uriBuilder) {
        SupervisorDto supervisorDto = SupervisorDto.map(supervisorService.update(id, supervisor));
        URI uri = uriBuilder
                .path("/supervisor/{id}")
                .buildAndExpand(id)
                .toUri();
        return ResponseEntity.created(uri).body(supervisorDto);
    }
}
