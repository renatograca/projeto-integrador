package com.concat.projetointegrador.controller;

import com.concat.projetointegrador.dto.SellerDTO;
import com.concat.projetointegrador.model.Seller;
import com.concat.projetointegrador.service.SellerService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    /**
     * create a seller
     * @param seller - seller object
     * @return a new seller
     */
    @PostMapping
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
    @GetMapping("/{id}")
    public ResponseEntity<SellerDTO> findByID(@PathVariable Long id) {
        SellerDTO seller = SellerDTO.convertToSellerDTO(sellerService.findByID(id));
        return ResponseEntity.ok(seller);
    }

    /**
     * Search all sellers
     * @return all searched sellers
     * * @return all sellers
     */
    @GetMapping
    public ResponseEntity<List<SellerDTO>> findAll() {
        List<SellerDTO> sellerDTO = SellerDTO.convertToListSellerDTO(sellerService.findAll());
        return ResponseEntity.ok(sellerDTO);

    }

    /**
     * Update seller
     * @param seller - seller object
     * @param id Long - seller id
     * @return updated seller
     */
    @PutMapping ("/{id}")
    public ResponseEntity <SellerDTO> update (@RequestBody Seller seller, @PathVariable Long id){
        SellerDTO s = SellerDTO.convertToSellerDTO(sellerService.update(seller,id));
        return ResponseEntity.ok(s);
    }

}

