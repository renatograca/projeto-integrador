package com.concat.projetointegrador.dto;

import com.concat.projetointegrador.model.BatchStock;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class WarehouseQuantityProductDTO {
    private Long warehouseCode;
    private Integer totalQuantity;

    public static WarehouseQuantityProductDTO map(Integer quantity, Long warehouseId) {
        return WarehouseQuantityProductDTO.builder().warehouseCode(warehouseId).totalQuantity(quantity).build();
    }
}
