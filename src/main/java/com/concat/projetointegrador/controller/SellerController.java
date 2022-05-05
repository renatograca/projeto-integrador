package com.concat.projetointegrador.controller;

import com.concat.projetointegrador.dto.SellerDTO;
import com.concat.projetointegrador.dto.SellerResponseDTO;
import com.concat.projetointegrador.model.Seller;
import com.concat.projetointegrador.service.SellerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
public class SellerController {

    private final SellerService sellerService;


    /**
     * create a seller
     * @param seller - seller object
     * @param uriComponentsBuilder
     * @return a new seller
     */
    @PostMapping("/seller")
    public ResponseEntity<SellerDTO> create(@RequestBody @Valid Seller seller, UriComponentsBuilder uriComponentsBuilder) {
        SellerDTO newSeller = SellerDTO.convertToSellerDTO(sellerService.create(seller));
        URI uri = uriComponentsBuilder.path("/seller/{id}")
                .buildAndExpand(newSeller.getId())
                .toUri();
        return ResponseEntity.created(uri).body(newSeller);
    }


    /**
     * search for a seller by id
     * @param id Long - seller id
     * @return a seller
     */
    @GetMapping("/seller/{id}")
    public ResponseEntity<SellerDTO> findByID(@PathVariable Long id) {
        SellerDTO seller = SellerDTO.convertToSellerDTO(sellerService.findByID(id));
        return ResponseEntity.ok(seller);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<SellerResponseDTO> findAllProductsBySeller(@PathVariable Long id) {
        Seller seller = sellerService.findByID(id);
        SellerResponseDTO sellerResponseDTO = SellerResponseDTO.convertToSellerResponseDTO(seller, sellerService.findAllProductsBySeller(id));
        return ResponseEntity.ok(sellerResponseDTO);
    }
}
