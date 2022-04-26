package com.concat.projetointegrador.service;

import java.util.Collection;
import java.util.Optional;

import com.concat.projetointegrador.model.BatchStock;
import com.concat.projetointegrador.model.Warehouse;
import org.springframework.stereotype.Service;

import com.concat.projetointegrador.exception.EntityNotFound;
import com.concat.projetointegrador.model.InboundOrder;
import com.concat.projetointegrador.repository.InboundOrderRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class InboundOrderService {

	private WarehouseService warehouseService;

	private InboundOrderRepository repository;

	public Collection<InboundOrder> findAllByActiveTrue() {
		return repository.findAllByActiveTrue();
	}

	private InboundOrder getInboundOrderById(Long id) {
		Optional<InboundOrder> opt = repository.findAllByIdAndActiveTrue(id);
		if (opt.isEmpty()) {
			throw new EntityNotFound("Ordem de entrada não encontrada!");
		}
		return opt.get();
	}

	public InboundOrder create(InboundOrder order) {

		warehouseService.findById(order.getSector().getWarehouse().getId());
		Integer total = 0;
		for (BatchStock batchStock : order.getBatchStock()) {

			total += batchStock.getProduct().getVolume();
			if (!batchStock.getProduct().getCategory().equals(order.getSector().getCategory())) {
				throw new RuntimeException("A categoria de todos os produtos deve ser igual ao do setor!");
			}
		}
		if (total > order.getSector().getCapacity()) {
			throw new RuntimeException("Capacidade total já atingida!");
		}

	// TODO autenticação supervisor
		return repository.save(order);
	}

	public InboundOrder update(Long id, InboundOrder order) {
		InboundOrder dbOrder = getInboundOrderById(id);

		if (order.getBatchStock() != null) {
			dbOrder.setBatchStock(order.getBatchStock());
		}
		
		if (order.getSector() != null) {
			dbOrder.setSector(order.getSector());
		}

		return repository.save(dbOrder);
	}

	public void delete(Long id) {
		InboundOrder inboundOrder = getInboundOrderById(id);
		inboundOrder.setActive(false);
		repository.save(inboundOrder);
	}

	public InboundOrder findAllByIdAndActiveTrue(Long id) {
		return this.getInboundOrderById(id);
	}

}
