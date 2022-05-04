package com.concat.projetointegrador.controller;

import com.concat.projetointegrador.model.Buyer;
import com.concat.projetointegrador.service.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class BuyerController {

		@Autowired
		private BuyerService buyerService;

		@PostMapping("/buyer")
		public ResponseEntity<Buyer> create(@RequestBody @Valid Buyer buyer){
				return new ResponseEntity<> (buyerService.create(buyer), HttpStatus.CREATED);
		}

}
