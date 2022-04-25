package com.concat.projetointegrador.controller;

import com.concat.projetointegrador.dto.ProductDTO;
import com.concat.projetointegrador.model.BatchStock;
import com.concat.projetointegrador.model.Product;
import com.concat.projetointegrador.service.BatchStockService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class BatchStockController {

    private BatchStockService batchStockService;

    @GetMapping("/batchstock/{id}")
    public ResponseEntity<BatchStock> findById(@PathVariable Long id) {
        return ResponseEntity.ok(batchStockService.findById(id));
    }

    @GetMapping("/batchstock")
    public ResponseEntity<List<BatchStock>> findAll() {
        return ResponseEntity.ok(batchStockService.findAll());
    }

    @PostMapping("/batchstock")
    public ResponseEntity<BatchStock> create(@RequestBody BatchStock batchStock) {
        return new ResponseEntity<>(batchStockService.create(batchStock), HttpStatus.CREATED);
    }

    @DeleteMapping("/batchstock/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        batchStockService.delete(id);
        return ResponseEntity.noContent().build();
    }
}