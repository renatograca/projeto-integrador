package com.concat.projetointegrador.service;

import com.concat.projetointegrador.dto.ProductDTO;
import com.concat.projetointegrador.exception.EntityNotFound;
import com.concat.projetointegrador.model.BatchStock;
import com.concat.projetointegrador.repository.BatchStockRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public void findByProductId(Long id, Integer quantity) {
        Optional<BatchStock> doesTheBatchStockExist = batchStockRepository.findByProductId(id);
        if(!doesTheBatchStockExist.isPresent()) {
            throw new EntityNotFound("Este produto não existe");
        }
        if(doesTheBatchStockExist.get().getCurrentQuantity() < quantity) {
            throw new EntityNotFound("Estoque insuficiente");
        }
    }
}
