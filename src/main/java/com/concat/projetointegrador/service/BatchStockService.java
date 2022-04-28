package com.concat.projetointegrador.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.concat.projetointegrador.exception.EntityNotFound;
import com.concat.projetointegrador.model.BatchStock;
import com.concat.projetointegrador.model.Product;
import com.concat.projetointegrador.repository.BatchStockRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BatchStockService {

    private BatchStockRepository batchStockRepository;

    public BatchStock findById(Long id) {
        Optional<BatchStock> batchStock = batchStockRepository.findById(id);
        if(batchStock.isPresent()){
            return batchStock.get();
        } else {
            throw new EntityNotFound("O estoque não foi encontrado!!");
        }
    }

    public List<BatchStock> findAll() {
        List<BatchStock> listBatchStock = batchStockRepository.findAll();
        return listBatchStock;
    }

    public BatchStock create(BatchStock batchStock) {
        return batchStockRepository.save(batchStock);
    }

    public void delete(Long id) {
        batchStockRepository.deleteById(id);
    }

    public BatchStock findByProductId(Long id, Integer quantity) {
        Optional<BatchStock> doesTheBatchStockExist = batchStockRepository.findByProductId(id);
        if(!doesTheBatchStockExist.isPresent()) {
            throw new EntityNotFound("Este produto não existe");
        }
        if(doesTheBatchStockExist.get().getCurrentQuantity() < quantity) {
            throw new EntityNotFound("Estoque insuficiente");
        }
        return doesTheBatchStockExist.get();
    }
	public List<BatchStock> findAllByProductId(Long id, String orderBy) {
        List<BatchStock> batchStocks = batchStockRepository.findAllByProductId(id);
        batchStocks = batchStocks
                    .stream()
                    .filter(batchStock
                                    -> (ChronoUnit.WEEKS.between(
                                    LocalDate.now(),
                                    batchStock.getDueDate()
                            ) >= 3) && (
                                    batchStock.getCurrentQuantity() > 0
                            )
                    ).collect(Collectors.toList());

        if (batchStocks.isEmpty()) {
            throw new EntityNotFound("Não foi encontrado nenhum lote para esse produto!");
        }
        if(orderBy == null) {
            orderBy = "";
        }

        switch (orderBy) {
            case "L":
                return batchStocks
                        .stream()
                        .sorted(Comparator.comparing(BatchStock::getId))
                        .collect(Collectors.toList());
            case "C":
                return batchStocks
                        .stream()
                        .sorted(Comparator.comparing(BatchStock::getCurrentQuantity))
                        .collect(Collectors.toList());
            case "F":
                return batchStocks
                        .stream()
                        .sorted(Comparator.comparing(BatchStock::getDueDate))
                        .collect(Collectors.toList());
        }

        return batchStocks;
	}
}
