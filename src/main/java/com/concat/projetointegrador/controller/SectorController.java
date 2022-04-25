package com.concat.projetointegrador.controller;

import com.concat.projetointegrador.dto.SectorDTO;
import com.concat.projetointegrador.model.Sector;
import com.concat.projetointegrador.repository.ISectorRepository;
import com.concat.projetointegrador.service.SectorService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

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
        Optional<Sector> sectorOptional = service.findById(id);
        if (sectorOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sector not found! Either enter with another ID or create a new sector!");
        }

        Sector sector = sectorOptional.get();
        sector.setCapacity(sectorDTO.getCapacity());
        return ResponseEntity.status(HttpStatus.OK).body(service.save(sector));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.delete(id));
    }
}
