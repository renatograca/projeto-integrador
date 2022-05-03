package com.concat.projetointegrador.controller;

import com.concat.projetointegrador.dto.SupervisorDTO;
import com.concat.projetointegrador.model.Supervisor;
import com.concat.projetointegrador.service.SupervisorService;
import org.springframework.beans.factory.annotation.Autowired;
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

		@PostMapping
		public ResponseEntity<SupervisorDTO> create(@RequestBody SupervisorDTO supervisorDto, UriComponentsBuilder uriBuilder) {
				Supervisor supervisor = Supervisor.builder()
								.name(supervisorDto.getName())
								.lastName(supervisorDto.getLastName())
								.password(supervisorDto.getPassword())
								.username(supervisorDto.getUserName())
								.build();

				Supervisor newSupervisor = supervisorService.create(supervisor);
				SupervisorDTO supervisorReturn = SupervisorDTO.map(newSupervisor);
				URI uri = uriBuilder
								.path("/supervisor/{id}")
								.buildAndExpand(newSupervisor.getId())
								.toUri();

				return ResponseEntity.created(uri).body(supervisorReturn);
		}

}