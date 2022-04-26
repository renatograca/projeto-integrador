package com.concat.projetointegrador.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.concat.projetointegrador.exception.EntityNotFound;
import com.concat.projetointegrador.model.InboundOrder;
import com.concat.projetointegrador.repository.InboundOrderRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class InboundOrderService {

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
		order.setActive(true);
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
