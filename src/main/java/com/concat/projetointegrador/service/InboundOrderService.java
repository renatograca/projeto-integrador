package com.concat.projetointegrador.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

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
		
		List<BatchStock> batchStocksBySector = batchStockRepository.findAllByInboundOrderSectorId(order.getSector().getId());
		Integer volume = batchStocksBySector
				.stream()
				.reduce(0,(acc, e) -> acc + (e.getProduct().getVolume() * e.getCurrentQuantity()), Integer::sum);
		
		Integer newBatchVolume = 0;
		for (BatchStock batchStock : order.getBatchStock()) {
			newBatchVolume += batchStock.getProduct().getVolume() * batchStock.getInitialQuantity();
			if (!batchStock.getProduct().getCategory().equals(order.getSector().getCategory())) {
				throw new RuntimeException("A categoria de todos os produtos deve ser igual ao do setor!");
			}
		}
		
		boolean volumeSmallerThanNewBatch = newBatchVolume > order.getSector().getCapacity() - volume;
		boolean newVolumeSmallerThanIntialCapacity = volume > order.getSector().getCapacity();
		if (volumeSmallerThanNewBatch || newVolumeSmallerThanIntialCapacity) {
			throw new RuntimeException("Capacidade total já atingida!");
		}
		
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
