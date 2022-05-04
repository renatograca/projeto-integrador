package com.concat.projetointegrador.controller;

import com.concat.projetointegrador.dto.SellerDTO;
import com.concat.projetointegrador.model.Seller;
import com.concat.projetointegrador.service.SellerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@AllArgsConstructor
public class SellerController {

		private final SellerService sellerService;


		/**
		 *
		 * @param seller user info
		 * @param uriComponentsBuilder
		 * @return new seller
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
		 *
		 * @param id - long
		 * @return seller
		 */
		@GetMapping("/seller/{id}")
		public ResponseEntity<Seller> findByID(@PathVariable Long id) {
				Seller seller = sellerService.findByID(id);
				return ResponseEntity.ok(seller);
		}
}
