package com.concat.projetointegrador.DTO;

import com.concat.projetointegrador.model.WarehouseModel;
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
    private String regiao;

    public static WarehouseDTO convertToWarehouseDTO(WarehouseModel warehouseModel) {
        return WarehouseDTO.builder()
                .id(warehouseModel.getId())
                .regiao(warehouseModel.getRegiao())
                .name(warehouseModel.getName()).build();
    }

    public static List<WarehouseDTO> convertToListWarehouse(List<WarehouseModel> listWarehouse) {
        return listWarehouse.stream()
                .map(warehouse -> new WarehouseDTO(
                        warehouse.getId(),
                        warehouse.getName(),
                        warehouse.getRegiao()))
                .collect(Collectors.toList());
    }

    public static WarehouseModel map(WarehouseDTO warehouseDTO) {
        return WarehouseModel.builder()
                .id(warehouseDTO.getId())
                .regiao(warehouseDTO.getRegiao())
                .name(warehouseDTO.getName()).build();
    }
}