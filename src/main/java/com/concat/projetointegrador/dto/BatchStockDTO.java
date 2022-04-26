package com.concat.projetointegrador.dto;

import com.concat.projetointegrador.model.BatchStock;
import com.concat.projetointegrador.model.Category;
import com.concat.projetointegrador.model.InboundOrder;
import com.concat.projetointegrador.model.Product;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BatchStockDTO {

    private Long id;
    private Category category;
    private Integer initialQuantity;
    private Integer currentQuantity;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate manufacturingDate;

    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime manufacturingTime;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dueDate;

    private Long productId;

    public static BatchStockDTO map(BatchStock batchStock) {
        BatchStockDTO batchStockDTO = new BatchStockDTO();
        BeanUtils.copyProperties(batchStock, batchStockDTO);
        batchStockDTO.setId(batchStock.getId());
        return batchStockDTO;
    }
}
