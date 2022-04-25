package com.concat.projetointegrador.controller;

import com.concat.projetointegrador.dto.SectorDTO;
import com.concat.projetointegrador.model.Sector;
import com.concat.projetointegrador.service.SectorService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/sector")
public class SectorController {

    private final SectorService service;

    public SectorController(SectorService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Object> saveSector(@RequestBody @Valid SectorDTO sectorDTO, UriComponentsBuilder uriBuilder) {
        Sector sector = new Sector();
        BeanUtils.copyProperties(sectorDTO, sector);
        service.save(sector);

        URI uri = uriBuilder
                .path("/sector/{id}")
                .buildAndExpand(sector.getId())
                .toUri();

        return ResponseEntity.created(uri).body(sector);
    }

    @GetMapping
    public ResponseEntity<List<Sector>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findOne(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(
            @PathVariable(value = "id") Long id,
            @RequestBody @Valid SectorDTO sectorDTO
    ) {
        Sector sector = service.findById(id);
        sector.setCapacity(sectorDTO.getCapacity());
        return ResponseEntity.status(HttpStatus.OK).body(service.save(sector));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.accepted().build();
    }
}
