package com.concat.projetointegrador.controller;

import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.concat.projetointegrador.exception.EntityNotFound;
import com.concat.projetointegrador.model.Sector;
import com.concat.projetointegrador.model.Warehouse;
import com.concat.projetointegrador.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.concat.projetointegrador.dto.InboundOrderDTO;
import com.concat.projetointegrador.model.BatchStock;
import com.concat.projetointegrador.model.InboundOrder;

@RestController
@RequestMapping("/fresh-products/inboundorder")
public class InboundOrderController {

    @Autowired
    private InboundOrderService orderService;

		@Autowired
    private BatchStockService batchStockService;

		@Autowired
		private WarehouseService warehouseService;

    @Autowired
    private SectorService sectorService;

    @Autowired
    private ProductService productService;

    @GetMapping
    /**
     * @return returns a list of inbound orders
     */
    public Collection<InboundOrder> findAllByActiveTrue() {
        return orderService.findAll();
    }

    @GetMapping("/{id}")
    /**
     * @param id - Long that represents the unique identifier
     * @return InboundOrder - returns an object with type InboundOrder
     */
    public InboundOrder findAllByIdAndActiveTrue(@PathVariable Long id) {
        return orderService.findById(id);
    }

    @PostMapping
    /**
     *
     * @param order - object with the data to registrate on database
     * @return returns the created database
     */
    public ResponseEntity<InboundOrder> create(@RequestBody InboundOrderDTO dto, UriComponentsBuilder uriBuilder) {//usado
		Sector sector = sectorService.findById(dto.getSector().getSectorCode());

		warehouseService.findById(dto.getSector().getWarehouseCode());

		InboundOrder inboundOrder = InboundOrderDTO.map(dto, sector);
    	
    	List<BatchStock> list = dto.getBatchStock()
    			.stream()
    			.map(
					e-> 
			    	BatchStock.builder()
			    		.category(sector.getCategory())
			    		.currentQuantity(e.getInitialQuantity())
			    		.dueDate(e.getDueDate())
						.initialTemperature(e.getInitialTemperature())
						.currentTemperature(e.getInitialTemperature())
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

    @PutMapping("/{id}")
    /**
     *
     * @param id - Long id that represents the inbound order on the database
     * @param order - object with the data to update
     * @return returns the updated inbound order
     */
    public ResponseEntity<InboundOrder> update(@PathVariable Long id, @RequestBody InboundOrderDTO dto) {
    	List<BatchStock> list = dto.getBatchStock()
    			.stream()
    			.map(
							e-> BatchStock.builder()
									.category(batchStockService.findById(id).getCategory())
					        .currentQuantity(e.getCurrentQuantity())
					        .dueDate(e.getDueDate())
					        .initialQuantity(e.getInitialQuantity())
									.initialTemperature(e.getInitialTemperature())
									.currentTemperature(e.getInitialTemperature())
					        .manufacturingDate(e.getManufacturingDate())
					        .manufacturingTime(e.getManufacturingTime())
					        .product(productService.findById(e.getProductId()))
					        .build()
					).collect(Collectors.toList());
    	InboundOrder inboundOrder = InboundOrderDTO.map(dto, sectorService.findById(dto.getSector().getSectorCode()), list);
    	inboundOrder = orderService.update(id, inboundOrder);
    	
    	return new ResponseEntity<>(inboundOrder, HttpStatus.CREATED);
    }

}
