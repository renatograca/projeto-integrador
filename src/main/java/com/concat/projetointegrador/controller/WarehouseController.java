package com.concat.projetointegrador.controller;

import com.concat.projetointegrador.dto.WarehouseDTO;
import com.concat.projetointegrador.dto.WarehouseQuantityProductDTO;
import com.concat.projetointegrador.dto.WarehouseResponseForQuantityProductsDTO;
import com.concat.projetointegrador.model.BatchStock;
import com.concat.projetointegrador.model.Warehouse;
import com.concat.projetointegrador.service.BatchStockService;
import com.concat.projetointegrador.service.InboundOrderService;
import com.concat.projetointegrador.service.SectorService;
import com.concat.projetointegrador.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    @Autowired
    private BatchStockService batchStockService;

    @Autowired
    private SectorService sectorService;

    @Autowired
    private InboundOrderService inboundOrderService;

    @GetMapping( "/warehouse/{id}")
    public ResponseEntity<WarehouseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(warehouseService.findById(id));
    }

    @GetMapping( "/warehouse")
    public ResponseEntity<List<WarehouseDTO>> findAll() {
        return ResponseEntity.ok(warehouseService.findAll());
    }


    @GetMapping("/fresh-products/warehouse")
    public ResponseEntity<WarehouseResponseForQuantityProductsDTO> findAllProductForWarehouse(@RequestParam(value = "querytype") Long productId) {
        List<BatchStock> batchProducts = batchStockService.findAllByProductId(productId);
        List<WarehouseQuantityProductDTO> allProductForWarehouse = warehouseService.findAllProductForWarehouse(batchProducts, inboundOrderService);
        WarehouseResponseForQuantityProductsDTO build = WarehouseResponseForQuantityProductsDTO.builder().productId(productId).warehouses(allProductForWarehouse).build();

        return ResponseEntity.ok(build);
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
