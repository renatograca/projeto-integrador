package com.concat.projetointegrador.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.BeanUtils;

import com.concat.projetointegrador.model.BatchStock;
import com.concat.projetointegrador.model.Category;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BatchStockDTO {

    private Long id;

    private Integer initialQuantity;
    private Integer currentQuantity;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate manufacturingDate;

    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime manufacturingTime;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dueDate;

    @NotNull(message = "a temperatura inicial não pode ser nula")
    private Integer initialTemperature;

    private Long productId;

    public static BatchStockDTO map(BatchStock batchStock) {
        BatchStockDTO batchStockDTO = new BatchStockDTO();
        BeanUtils.copyProperties(batchStock, batchStockDTO);
        batchStockDTO.setProductId(batchStock.getProduct().getId());
        return batchStockDTO;
    }
}
