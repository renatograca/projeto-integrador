package com.concat.projetointegrador.controller.sellerController;

import com.concat.projetointegrador.controller.sellerExceptions.NoSellerException;
import com.concat.projetointegrador.model.sellerModel.Seller;
import com.concat.projetointegrador.service.sellerService.SellerService;
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

    @PostMapping("/api/seller")
        public ResponseEntity<Seller> create(@RequestBody @Valid Seller seller, UriComponentsBuilder uriComponentsBuilder) {

        Seller newSeller = sellerService.create(seller);

        URI uri = uriComponentsBuilder.path("/api/seller/{id}")
                .buildAndExpand(newSeller.getId())
                .toUri();

        return ResponseEntity.created(uri).body(newSeller);

    }

    @GetMapping("/api/sellers")
        public ResponseEntity<List<Seller>> findAll() {

        List<Seller> sellers = sellerService.findAll();

        return ResponseEntity.ok(sellers);

    }

    @GetMapping("/api/seller/{id}")
        public ResponseEntity<Optional<Seller>> findByID(@PathVariable Long id) {

            Optional<Seller> seller = sellerService.findByID(id);

            return ResponseEntity.ok(seller);

    }

    @PutMapping("/api/seller/{id}")
        public ResponseEntity<Seller> updateByID(@PathVariable Long id, @RequestBody Seller seller) {

        Optional<Seller> doesTheSellerExist = sellerService.findByID(id);

            if(doesTheSellerExist.isEmpty()){

                throw new NoSellerException("Seller não existe.");

            } else {

                seller.setId(id);
                Seller updatedSeller = sellerService.update(seller);

                return ResponseEntity.ok(updatedSeller);

            }

    }

    @DeleteMapping("/api/seller/{id}")
        public ResponseEntity<Void> deleteByID(@PathVariable Long id) {

            sellerService.deleteByID(id);

            return ResponseEntity.noContent().build();

    }

}
