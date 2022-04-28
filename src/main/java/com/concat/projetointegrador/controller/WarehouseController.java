package com.concat.projetointegrador.controller;

import com.concat.projetointegrador.dto.WarehouseDTO;
import com.concat.projetointegrador.model.BatchStock;
import com.concat.projetointegrador.model.Warehouse;
import com.concat.projetointegrador.service.BatchStockService;
import com.concat.projetointegrador.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    @Autowired
    private BatchStockService batchStockService;

    @GetMapping( "/warehouse/{id}")
    public ResponseEntity<WarehouseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(warehouseService.findById(id));
    }

    @GetMapping( "/warehouse")
    public ResponseEntity<List<WarehouseDTO>> findAll() {
        return ResponseEntity.ok(warehouseService.findAll());
    }

    @GetMapping("/fresh-products/warehouse")
    public ResponseEntity<List<BatchStock>> findAllProductForWarehouse(@RequestParam(value = "querytype") Long productId) {
        List<BatchStock> batchProducts = batchStockService.findAllByProductId(productId);
        return ResponseEntity.ok(batchProducts);
    }
    @PostMapping("/warehouse")
    public ResponseEntity<WarehouseDTO> create(@RequestBody Warehouse warehouse) {
        return new ResponseEntity<>(warehouseService.create(warehouse),HttpStatus.CREATED);
    }

    @PutMapping("/warehouse/{id}")
    public ResponseEntity<WarehouseDTO> update(@RequestBody Warehouse warehouse, @PathVariable Long id) {
        return ResponseEntity.ok(warehouseService.update(warehouse, id));
    }

    @DeleteMapping("/warehouse/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        warehouseService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
