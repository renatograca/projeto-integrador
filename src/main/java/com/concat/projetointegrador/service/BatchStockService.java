package com.concat.projetointegrador.service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.concat.projetointegrador.dto.BatchStockFilterDTO;
import org.springframework.stereotype.Service;

import com.concat.projetointegrador.exception.EntityNotFound;
import com.concat.projetointegrador.model.BatchStock;
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

    public List<BatchStockFilterDTO> filterBatchStocksThatExpireInXDays(int numberOfDays) {
        LocalDate expireTilDate = LocalDate.now().plusDays(numberOfDays);
        List<BatchStock> batchStockList = findAll()
                .stream()
                .filter(batchStock -> expireTilDate.isAfter(batchStock.getDueDate()))
                .collect(Collectors.toList());

        return batchStockList
                .stream()
                .map(BatchStockFilterDTO::convertToDTO)
                .collect(Collectors.toList())
                .stream()
                .sorted(Comparator.comparing(BatchStockFilterDTO::getDueDate))
                .collect(Collectors.toList());
    }

    public BatchStock create(BatchStock batchStock) {
        return batchStockRepository.save(batchStock);
    }

    public void delete(Long id) {
        batchStockRepository.deleteById(id);
    }
}
