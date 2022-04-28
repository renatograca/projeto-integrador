package com.concat.projetointegrador.dto;

import com.concat.projetointegrador.model.BatchStock;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
public class BatchStockFilterDTO {

    private Long id;
    private Long productId;
    private String category;
    private LocalDate dueDate;
    private int quantity;

    public static BatchStockFilterDTO convertToDTO(BatchStock batchStock) {
        return new BatchStockFilterDTO(
                batchStock.getId(),
                batchStock.getProduct().getId(),
                batchStock.getCategory().name(),
                batchStock.getDueDate(),
                batchStock.getCurrentQuantity()
        );
    }
}
