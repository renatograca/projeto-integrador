package com.concat.projetointegrador.service;

import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.concat.projetointegrador.dto.BatchStockFilterDTO;
import com.concat.projetointegrador.model.InboundOrder;
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
        return batchStockRepository.findAll();
    }

    public List<BatchStockFilterDTO> filterBatchStocks(
            List<InboundOrder> inboundOrderList,
            int numberOfDays,
            String category,
            Integer asc
    ) throws InvalidParameterException {
        LocalDate expireTilDate = LocalDate.now().plusDays(numberOfDays);
        List<BatchStockFilterDTO> batchStockFilterDTOList;

        List<BatchStock> batchStockList = inboundOrderList
                .stream()
                .flatMap(inboundOrder -> inboundOrder.getBatchStock().stream())
                .collect(Collectors.toList());

        batchStockFilterDTOList = batchStockList
                .stream()
                .filter(batchStock -> expireTilDate.isAfter(batchStock.getDueDate()))
                .map(BatchStockFilterDTO::convertToDTO)
                .sorted(Comparator.comparing(BatchStockFilterDTO::getDueDate))
                .collect(Collectors.toList());

        if (category != null ) {
            batchStockFilterDTOList = batchStockFilterDTOList
                        .stream()
                        .filter(
                                batchStock -> batchStock.getCategory().equalsIgnoreCase(category)
                        )
                        .collect(Collectors.toList());
        }

        if(asc != null && asc == 0) {
            return batchStockFilterDTOList
                    .stream()
                    .sorted((b1, b2) -> b2.getDueDate().compareTo(b1.getDueDate()))
                    .collect(Collectors.toList());
        } else if (asc != null && asc != 1) {
            throw new InvalidParameterException("O valor de asc deve ser 0 ou 1!");
        }

        return batchStockFilterDTOList;
    }

    public BatchStock create(BatchStock batchStock) {
        return batchStockRepository.save(batchStock);
    }

    public void delete(Long id) {
        batchStockRepository.deleteById(id);
    }

    public List<BatchStock> findByProductId(Long id, Integer quantity) {
        List<BatchStock> doesTheBatchStockExist = getBatchStocks(id);

        if(doesTheBatchStockExist.isEmpty()) {
            throw new EntityNotFound("Este produto não existe");
        }

        Integer produtcQuantityTotal= doesTheBatchStockExist.stream().reduce(0, (acc, e) -> acc + e.getCurrentQuantity(), Integer::sum);

        if(produtcQuantityTotal < quantity) {
            throw new EntityNotFound("Estoque insuficiente");
        }
        return doesTheBatchStockExist;
    }

	public List<BatchStock> findAllByProductId(Long id, String orderBy) {
        List<BatchStock> batchStocks = getBatchStocks(id);

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

    private List<BatchStock> getBatchStocks(Long id) {
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

        return batchStocks;
    }
}
