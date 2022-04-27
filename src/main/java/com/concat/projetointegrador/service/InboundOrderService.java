package com.concat.projetointegrador.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.concat.projetointegrador.service.validator.ValidateCategoryMatch;
import com.concat.projetointegrador.service.validator.ValidateSectorCapacity;
import com.concat.projetointegrador.service.validator.Validator;
import lombok.NoArgsConstructor;
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

	private WarehouseService warehouseService;
	private InboundOrderRepository repository;
	private BatchStockRepository batchStockRepository;
	private List<Validator> validators;

	public void initializeValidators(InboundOrder order) {
		this.validators = Arrays.asList(
				new ValidateSectorCapacity(order, batchStockRepository),
				new ValidateCategoryMatch(order)
		);
	}

	public List<InboundOrder> findAllByActiveTrue() {
		return repository.findAll();
	}

	private InboundOrder getInboundOrderById(Long id) {
		Optional<InboundOrder> opt = repository.findById(id);
		if (opt.isEmpty()) {
			throw new EntityNotFound("Ordem de entrada não encontrada!");
		}

		return opt.get();
	}

	public InboundOrder create(InboundOrder order) {

		warehouseService.findById(order.getSector().getWarehouse().getId());

		initializeValidators(order);
		validators.forEach(Validator::validate);

		InboundOrder newInboundOrder = repository.save(order);
		order.getBatchStock().forEach(e-> {
				e.setInboundOrder(newInboundOrder);
		});

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

	public void delete(Long id) {
		repository.deleteById(id);
	}

	public InboundOrder findById(Long id) {
		return this.getInboundOrderById(id);
	}

}
