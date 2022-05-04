package com.concat.projetointegrador.controller;

import com.concat.projetointegrador.dto.BuyerDTO;
import com.concat.projetointegrador.model.Buyer;
import com.concat.projetointegrador.service.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/buyer")
public class BuyerController {

		@Autowired
		private BuyerService buyerService;

		@PostMapping
		public ResponseEntity<BuyerDTO> create(@RequestBody @Valid Buyer buyer) {
				BuyerDTO buyerDTO = BuyerDTO.convertToBuyerDTO(buyerService.create(buyer));
				return new ResponseEntity<>(buyerDTO, HttpStatus.CREATED);
		}

}
