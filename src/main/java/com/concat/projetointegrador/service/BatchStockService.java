package com.concat.projetointegrador.service;

import com.concat.projetointegrador.model.BatchStock;
import com.concat.projetointegrador.repository.BatchStockRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BatchStockService {

    private BatchStockRepository batchStockRepository;

    public BatchStock findById(Long id) {
        return batchStockRepository.findById(id).orElse(new BatchStock());
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
}
