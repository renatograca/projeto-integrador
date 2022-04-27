package com.concat.projetointegrador.controller;

import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.concat.projetointegrador.dto.InboundOrderDTO;
import com.concat.projetointegrador.model.BatchStock;
import com.concat.projetointegrador.model.InboundOrder;
import com.concat.projetointegrador.service.InboundOrderService;
import com.concat.projetointegrador.service.ProductService;
import com.concat.projetointegrador.service.SectorService;

@RestController
@RequestMapping("/fresh-products/inboundorder")
public class InboundOrderController {

    @Autowired
    private InboundOrderService orderService;

    @Autowired
    private SectorService sectorService;

    @Autowired
    private ProductService productService;

    @GetMapping
    public Collection<InboundOrder> findAllByActiveTrue() {
        return orderService.findAllByActiveTrue();
    }

    @GetMapping("/{id}")
    public InboundOrder findAllByIdAndActiveTrue(@PathVariable Long id) {
        return orderService.findAllByIdAndActiveTrue(id);
    }

    @PostMapping
    public ResponseEntity<InboundOrder> create(@RequestBody InboundOrderDTO dto, UriComponentsBuilder uriBuilder) {
    	
    	InboundOrder inboundOrder = InboundOrderDTO.map(dto, sectorService.findById(dto.getSector().getSectorCode()));
    	
    	List<BatchStock> list = dto.getBatchStock()
    			.stream()
    			.map(
					e-> 
			    	BatchStock.builder()
			    		.category(e.getCategory())
			    		.currentQuantity(e.getCurrentQuantity())
			    		.dueDate(e.getDueDate())
			    		.initialQuantity(e.getInitialQuantity())
			    		.manufacturingDate(e.getManufacturingDate())
			    		.manufacturingTime(e.getManufacturingTime())
			    		.product(productService.findById(e.getProductId()))
			    		.build()
				).collect(Collectors.toList());
    	
    	inboundOrder.setBatchStock(list);
    	
    	InboundOrder order = orderService.create(inboundOrder);
    	
        URI uri = uriBuilder.path("/fresh-products/inboundorder/{id}").buildAndExpand(order.getId()).toUri();
        
        return ResponseEntity.created(uri).body(order);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<InboundOrder> update(@PathVariable Long id, @RequestBody InboundOrderDTO dto) {
//        InboundOrder order = orderService.create(InboundOrderDTO.map(dto, sectorService.findById(dto.getSector().getSectorCode()),
//                dto.getBatchStock().stream().map(batchStockDTO -> batchStockService.findById(batchStockDTO.getId())).collect(Collectors.toList())));
//        return ResponseEntity.ok(order);
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        orderService.delete(id);
        return ResponseEntity.accepted().build();
    }
}
