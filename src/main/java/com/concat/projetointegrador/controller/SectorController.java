package com.concat.projetointegrador.controller;

import com.concat.projetointegrador.dto.SectorDTO;
import com.concat.projetointegrador.model.Sector;
import com.concat.projetointegrador.service.SectorService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/sector")
public class SectorController {

    private final SectorService service;

    public SectorController(SectorService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Object> saveSector(@RequestBody @Valid SectorDTO sectorDTO) {
        Sector sector = new Sector();
        BeanUtils.copyProperties(sectorDTO, sector);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(sector));
    }

    @GetMapping
    public ResponseEntity<List<Sector>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }
}
