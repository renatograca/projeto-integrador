package com.concat.projetointegrador.controller.sellerController;

import com.concat.projetointegrador.model.sellerModel.Seller;
import com.concat.projetointegrador.service.sellerService.SellerService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

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

}
