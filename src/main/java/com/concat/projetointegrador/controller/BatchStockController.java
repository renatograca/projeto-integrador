package com.concat.projetointegrador.controller;

import com.concat.projetointegrador.dto.BatchStockFilterDTO;
import com.concat.projetointegrador.model.BatchStock;
import com.concat.projetointegrador.model.InboundOrder;
import com.concat.projetointegrador.service.BatchStockService;
import com.concat.projetointegrador.service.InboundOrderService;
import com.concat.projetointegrador.service.SectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.InvalidParameterException;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/batchstock")
public class BatchStockController {

    @Autowired
    private BatchStockService batchStockService;

    @Autowired
    private InboundOrderService inboundOrderService;

    @Autowired
    private SectorService sectorService;

    @GetMapping("/{id}")
    public ResponseEntity<BatchStock> findById(@PathVariable Long id) {
        return ResponseEntity.ok(batchStockService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<BatchStock>> findAll() {
        return ResponseEntity.ok(batchStockService.findAll());
    }

    @PostMapping
    public ResponseEntity<BatchStock> create(@RequestBody @Valid BatchStock batchStock) {
        return new ResponseEntity<>(batchStockService.create(batchStock), HttpStatus.CREATED);
    }

    @GetMapping("/duedate")
    public ResponseEntity<List<BatchStockFilterDTO>> filter(
            @RequestParam int days,
            @RequestParam Long sectorId,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Integer asc
    ) throws InvalidParameterException {
        sectorService.findById(sectorId);
        List<InboundOrder> inboundOrderList = inboundOrderService.findBySectorId(sectorId);
        List<BatchStockFilterDTO> batchStockFilterDTOList = batchStockService
                .filterBatchStocks(inboundOrderList,days, category, asc);
        return ResponseEntity.status(HttpStatus.OK).body(batchStockFilterDTOList);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        batchStockService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
