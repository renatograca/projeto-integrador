package com.concat.projetointegrador.controller;

import com.concat.projetointegrador.dto.BestSellerDTO;
import com.concat.projetointegrador.dto.SellerDTO;
import com.concat.projetointegrador.model.PurchasedOrder;
import com.concat.projetointegrador.model.Seller;
import com.concat.projetointegrador.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    /**
     * create a seller
     * @param seller - seller object
     * @param uriComponentsBuilder
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
     * Search sellers
     * @return a List of BestSellerDTO ordered by quantity of sales
     */
    @GetMapping("/bests")
    public ResponseEntity<List<BestSellerDTO>> findBestSellers() {

        HashMap<Long, Integer> sellers = sellerService.findBestSellers();

        List<BestSellerDTO> bestSellers = sellers.entrySet().stream()
                .map(seller -> BestSellerDTO.builder()
                .id(seller.getKey())
                .username(sellerService.findByID(seller.getKey()).getUsername())
                .quantityOfProductsSale(seller.getValue())
                .build()).collect(Collectors.toList());

        Comparator<BestSellerDTO> compareByQuantityOfProductsSale = Comparator
                .comparing(BestSellerDTO::getQuantityOfProductsSale).reversed();

        bestSellers.sort(compareByQuantityOfProductsSale);

        return ResponseEntity.ok(bestSellers);

    }

}
