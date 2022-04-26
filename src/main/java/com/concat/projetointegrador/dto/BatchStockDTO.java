package com.concat.projetointegrador.dto;

import com.concat.projetointegrador.model.Category;
import com.concat.projetointegrador.model.Product;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
}
