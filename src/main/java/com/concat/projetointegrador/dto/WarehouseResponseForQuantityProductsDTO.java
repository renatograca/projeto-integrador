package com.concat.projetointegrador.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
public class WarehouseResponseForQuantityProductsDTO {
    private Long productId;
    private List<WarehouseQuantityProductDTO> warehouses;

    public static Map<Long, Integer> sumQuantityForWarehouse(List<WarehouseQuantityProductDTO> warehouses) {
        Map<Long, Integer> map = new HashMap<>();
        warehouses.forEach(e -> {
            if(map.containsKey(e.getWarehouseCode())) {
                map.put(e.getWarehouseCode(), e.getTotalQuantity() + map.get(e.getWarehouseCode()));
            } else {
                map.put(e.getWarehouseCode(), e.getTotalQuantity());
            }
        });
        return map;
    }

}
