package com.concat.projetointegrador.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        List<BatchStock> listBatchStock = batchStockRepository.findAll();
        return listBatchStock;
    }

    public List<BatchStock> filterBatchStocksThatExpireInXDays(int numberOfDays) {
        LocalDate expireTilDate = LocalDate.now().plusDays(numberOfDays);
        return findAll()
                .stream()
                .filter(batchStock -> expireTilDate.isAfter(batchStock.getDueDate()))
                .collect(Collectors.toList());
    }

    public BatchStock create(BatchStock batchStock) {
        return batchStockRepository.save(batchStock);
    }

    public void delete(Long id) {
        batchStockRepository.deleteById(id);
    }
}
