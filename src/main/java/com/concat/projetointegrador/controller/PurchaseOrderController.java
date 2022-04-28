package com.concat.projetointegrador.controller;

import com.concat.projetointegrador.dto.PurchasedOrderDTO;
import com.concat.projetointegrador.model.PurchasedOrder;
import com.concat.projetointegrador.service.PurchasedOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PurchaseOrderController {

    @Autowired
    private PurchasedOrderService purchaseOrderService;

    @PostMapping("/purchasedOrder")
    public ResponseEntity<PurchasedOrderDTO> create(@RequestBody PurchasedOrder purchasedOrder) {
        return new ResponseEntity<>(purchaseOrderService.create(purchasedOrder), HttpStatus.CREATED);
    }

    @GetMapping("/purchasedOrder")
    public ResponseEntity<PurchasedOrder> findById(@RequestParam Long id) {
        return ResponseEntity.ok(purchaseOrderService.findById(id));
    }

    @PutMapping("/purchasedOrder")
    public ResponseEntity<PurchasedOrder> update(@RequestParam Long id) {
        return ResponseEntity.ok(purchaseOrderService.update(id));
    }

}
