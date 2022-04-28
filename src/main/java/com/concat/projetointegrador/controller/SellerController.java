package com.concat.projetointegrador.controller;

import com.concat.projetointegrador.model.Seller;
import com.concat.projetointegrador.service.SellerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class SellerController {

    private final SellerService sellerService;

    @PostMapping("/seller")
    public ResponseEntity<Seller> create(@RequestBody @Valid Seller seller, UriComponentsBuilder uriComponentsBuilder) {
        Seller newSeller = sellerService.create(seller);
        URI uri = uriComponentsBuilder.path("/seller/{id}")
                .buildAndExpand(newSeller.getId())
                .toUri();
        return ResponseEntity.created(uri).body(newSeller);
    }

    @GetMapping("/sellers")
    public ResponseEntity<List<Seller>> findAll() {
        List<Seller> sellers = sellerService.findAll();
        return ResponseEntity.ok(sellers);
    }

    @GetMapping("/seller/{id}")
    public ResponseEntity<Seller> findByID(@PathVariable Long id) {
        Seller seller = sellerService.findByID(id);
        return ResponseEntity.ok(seller);
    }

    @PutMapping("/seller/{id}")
    public ResponseEntity<Seller> update(@PathVariable Long id, @RequestBody @Valid Seller seller) {
        Seller updatedSeller = sellerService.update(seller, id);
        return ResponseEntity.ok(updatedSeller);
    }

    @DeleteMapping("/seller/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        sellerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
