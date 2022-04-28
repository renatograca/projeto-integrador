package com.concat.projetointegrador.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class warehouseQuantityProduct {
    private Long warehouseCode;
    private Integer totalQuantity;
}
