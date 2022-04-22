package com.concat.projetointegrador.controller.sellerController;

import com.concat.projetointegrador.model.sellerModel.Seller;
import com.concat.projetointegrador.service.sellerService.SellerService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class SellerController {

    private SellerService sellerService;

    @PostMapping("/api/seller")
        public ResponseEntity<Seller> createSeller(@RequestBody Seller seller, UriComponentsBuilder uriComponentsBuilder) {

        Seller newSeller = sellerService.createSeller(seller);

        URI uri = uriComponentsBuilder.path("/api/seller/{id}")
                .buildAndExpand(newSeller.getId())
                .toUri();

        return ResponseEntity.created(uri).body(newSeller);

    }

    @GetMapping("/api/sellers")
        public ResponseEntity<List<Seller>> getSellers() {

        List<Seller> sellers = sellerService.getSellers();

        return ResponseEntity.ok(sellers);

    }

    @GetMapping("/api/seller/{id}")
        public ResponseEntity<Optional<Seller>> getSellerByID(@PathVariable Long id) {

            Optional<Seller> seller = sellerService.getSellerByID(id);

            return ResponseEntity.ok(seller);

    }

    @PutMapping("/api/seller/{id}")
        public ResponseEntity<Seller> updateSellerByID(@PathVariable Long id, @RequestBody Seller seller) {

            seller.setId(id);
            Seller updatedSeller = sellerService.updateSeller(seller);

            return ResponseEntity.ok(updatedSeller);

    }


}
