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
import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    @Autowired
    private BatchStockService batchStockService;

    @Autowired
    private InboundOrderService inboundOrderService;

    /**
     * Search warehouse by id
     * @param id - Long ID warehouse
     * @return the warehouse when find with status 200 OK
     */
    @GetMapping( "/{id}")
    public ResponseEntity<WarehouseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(warehouseService.findById(id));
    }

    /**
     * Search a products list by warehouse
     * @param productId - Long ID product
     * @return A list of products by warehouse
     */
    @GetMapping("/products/warehouse")
    public ResponseEntity<WarehouseResponseForQuantityProductsDTO> findAllProductForWarehouse(@RequestParam(value = "productId") Long productId) {
        List<BatchStock> batchProducts = batchStockService.findAllByProductId(productId, null);
        List<WarehouseQuantityProductDTO> allProductForWarehouse = warehouseService.findAllProductForWarehouse(batchProducts, inboundOrderService);
        WarehouseResponseForQuantityProductsDTO build = WarehouseResponseForQuantityProductsDTO.builder().productId(productId).warehouses(allProductForWarehouse).build();

        return ResponseEntity.ok(build);
    }

    /**
     * Register a new warehouse
     * @param warehouse - An object with the data of the store (name and region)
     * @return  The object that was registered with the status 201 created
     */
    @PostMapping
    public ResponseEntity<WarehouseDTO> create(@RequestBody @Valid Warehouse warehouse) {
        return new ResponseEntity<>(warehouseService.create(warehouse),HttpStatus.CREATED);
    }
}
