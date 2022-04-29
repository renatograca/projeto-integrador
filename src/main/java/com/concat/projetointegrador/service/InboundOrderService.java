package com.concat.projetointegrador.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.concat.projetointegrador.service.validator.SectorCapacityValidate;
import com.concat.projetointegrador.service.validator.SectorCategoryMatchValidate;
import com.concat.projetointegrador.service.validator.Validator;
import org.springframework.stereotype.Service;

import com.concat.projetointegrador.exception.EntityNotFound;
import com.concat.projetointegrador.model.BatchStock;
import com.concat.projetointegrador.model.InboundOrder;
import com.concat.projetointegrador.repository.BatchStockRepository;
import com.concat.projetointegrador.repository.InboundOrderRepository;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class InboundOrderService {

	private InboundOrderRepository repository;
	
	private BatchStockRepository batchStockRepository;

	private List<Validator> validators;

	private void initializeValidators(InboundOrder order) {
		this.validators = Arrays.asList(
				new SectorCapacityValidate(order, batchStockRepository),
				new SectorCategoryMatchValidate(order)
		);
	}

	private InboundOrder getInboundOrderById(Long id) {
		Optional<InboundOrder> opt = repository.findById(id);
		if (opt.isEmpty()) {
			throw new EntityNotFound("Ordem de entrada não encontrada!");
		}
		return opt.get();
	}

	public InboundOrder create(InboundOrder order) {
		initializeValidators(order);
		validators.forEach(Validator::validate);

		InboundOrder newInboundOrder = repository.save(order);
		order.getBatchStock().forEach(e-> e.setInboundOrder(newInboundOrder));

		List<BatchStock> newBatchStocks = batchStockRepository.saveAll(newInboundOrder.getBatchStock());

		newInboundOrder.setBatchStock(newBatchStocks);
		repository.save(newInboundOrder);
		
	// TODO autenticação supervisor
		return newInboundOrder;
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

	public List<InboundOrder> findBySectorId(Long sectorId) {
		return findAll()
				.stream()
				.filter(inboundOrder -> inboundOrder.getSector().getId().equals(sectorId))
				.collect(Collectors.toList());
	}

	public InboundOrder findById(Long id) {
		return this.getInboundOrderById(id);
	}

	public List<InboundOrder> findAll() {
		return repository.findAll();
	}

}
