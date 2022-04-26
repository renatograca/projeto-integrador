package com.concat.projetointegrador.controller;

import java.net.URI;
import java.util.Collection;

import com.concat.projetointegrador.dto.SectorDTO;
import com.concat.projetointegrador.dto.InboundOrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.concat.projetointegrador.model.InboundOrder;
import com.concat.projetointegrador.service.InboundOrderService;

@RestController
@RequestMapping("/fresh-products/inboundorder")
public class InboundOrderController {

	@Autowired
	private InboundOrderService service;

	@GetMapping
	public Collection<InboundOrder> findAllByActiveTrue() {
		return service.findAllByActiveTrue();
	}

	@GetMapping("/{id}")
	public InboundOrder findAllByIdAndActiveTrue(@PathVariable Long id) {
		return service.findAllByIdAndActiveTrue(id);
	}

	@PostMapping
    public ResponseEntity<InboundOrder> create(@RequestBody InboundOrderDTO dto, UriComponentsBuilder uriBuilder){
    	InboundOrder order = service.create(InboundOrderDTO.map(dto));
    	URI uri = uriBuilder.path("/fresh-products/inboundorder/{id}").buildAndExpand(order.getId()).toUri();
        return ResponseEntity.created(uri).body(order);
    }

	@PutMapping("/{id}")
    public ResponseEntity<InboundOrder> update(@PathVariable Long id, @RequestBody InboundOrderDTO dto) {
    	InboundOrder order = service.update(id, InboundOrderDTO.map(dto));
		return ResponseEntity.ok(order);
    }
	
	@DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
    	service.delete(id);
		return ResponseEntity.accepted().build();
    }

}
