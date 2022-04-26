package com.concat.projetointegrador.controller;

import com.concat.projetointegrador.model.BatchStock;
import com.concat.projetointegrador.service.BatchStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/batchstock")
public class BatchStockController {

    @Autowired
    private BatchStockService batchStockService;

    @GetMapping("/{id}")
    public ResponseEntity<BatchStock> findById(@PathVariable Long id) {
        return ResponseEntity.ok(batchStockService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<BatchStock>> findAll() {
        return ResponseEntity.ok(batchStockService.findAll());
    }

    @PostMapping
    public ResponseEntity<BatchStock> create(@RequestBody BatchStock batchStock) {
        return new ResponseEntity<>(batchStockService.create(batchStock), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        batchStockService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
