package com.concat.projetointegrador.dto;

import com.concat.projetointegrador.model.Warehouse;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseDTO {

    private Long id;
    private String name;
    private String region;

    public static WarehouseDTO convertToWarehouseDTO(Warehouse warehouse) {
        return WarehouseDTO.builder()
                .id(warehouse.getId())
                .region(warehouse.getRegion())
                .name(warehouse.getName()).build();
    }

    public static List<WarehouseDTO> convertToListWarehouse(List<Warehouse> listWarehouse) {
        return listWarehouse.stream()
                .map(warehouse -> new WarehouseDTO(
                        warehouse.getId(),
                        warehouse.getName(),
                        warehouse.getRegion()))
                .collect(Collectors.toList());
    }

    public static Warehouse map(WarehouseDTO warehouseDTO) {
        return Warehouse.builder()
                .id(warehouseDTO.getId())
                .region(warehouseDTO.getRegion())
                .name(warehouseDTO.getName()).build();
    }
}
