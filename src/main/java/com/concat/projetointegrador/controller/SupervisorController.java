package com.concat.projetointegrador.controller;

import com.concat.projetointegrador.dto.SupervisorDTO;
import com.concat.projetointegrador.model.SupervisorModel;
import com.concat.projetointegrador.service.SupervisorService;
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
    public ResponseEntity<SupervisorDTO> create(@RequestBody SupervisorDTO supervisorDto, UriComponentsBuilder uriBuilder) {
        SupervisorModel supervisor = SupervisorModel.builder().name(supervisorDto.getName()).lastname(supervisorDto.getLastname()).build();
        SupervisorModel newSupervisor = supervisorService.create(supervisor);
        SupervisorDTO supervisorReturn = SupervisorDTO.map(newSupervisor);
        URI uri = uriBuilder
                .path("/supervisor/{id}")
                .buildAndExpand(newSupervisor.getId())
                .toUri();
        return ResponseEntity.created(uri).body(supervisorReturn);
    }

    @GetMapping
    public ResponseEntity<List<SupervisorDTO>> list() {
        List<SupervisorModel> supervisorModels = supervisorService.list();
        List<SupervisorDTO> supervisorDtos = new ArrayList<>();

        supervisorModels.forEach(e -> supervisorDtos.add(SupervisorDTO.map(e)));
        return ResponseEntity.ok(supervisorDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupervisorDTO> getSupervisor(@PathVariable Long id) {
        SupervisorDTO supervisorDto = SupervisorDTO.map(supervisorService.getSupervisor(id));
        return ResponseEntity.ok(supervisorDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SupervisorDTO> update(@PathVariable Long id, @RequestBody SupervisorDTO supervisor, UriComponentsBuilder uriBuilder) {
        SupervisorDTO supervisorDto = SupervisorDTO.map(supervisorService.update(id, supervisor));
        URI uri = uriBuilder
                .path("/supervisor/{id}")
                .buildAndExpand(id)
                .toUri();
        return ResponseEntity.created(uri).body(supervisorDto);
    }
}
