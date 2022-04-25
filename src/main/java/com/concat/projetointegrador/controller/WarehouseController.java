package com.concat.projetointegrador.controller;

import com.concat.projetointegrador.DTO.WarehouseDTO;
import com.concat.projetointegrador.model.WarehouseModel;
import com.concat.projetointegrador.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    @GetMapping( "/warehouse/{id}")
    public ResponseEntity<WarehouseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(warehouseService.findById(id));
    }

    @GetMapping( "/warehouse")
    public ResponseEntity<WarehouseDTO> findAll() {
        return ResponseEntity.ok(warehouseService.findAll());
    }

    @PostMapping("/warehouse")
    public ResponseEntity<WarehouseDTO> create(@RequestBody WarehouseModel warehouseModel) {
        return new ResponseEntity<>(warehouseService.create(warehouseModel),HttpStatus.CREATED);
    }

    @PutMapping("/warehouse/{id}")
    public ResponseEntity<WarehouseDTO> update(@RequestBody WarehouseModel warehouseModel, @PathVariable Long id) {
        return ResponseEntity.ok(warehouseService.update(warehouseModel, id));
    }

    @DeleteMapping("/warehouse/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        warehouseService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
