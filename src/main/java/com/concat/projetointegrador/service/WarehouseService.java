package com.concat.projetointegrador.service;

import com.concat.projetointegrador.dto.WarehouseDTO;
import com.concat.projetointegrador.dto.WarehouseQuantityProductDTO;
import com.concat.projetointegrador.dto.WarehouseResponseForQuantityProductsDTO;
import com.concat.projetointegrador.exception.EntityNotFound;
import com.concat.projetointegrador.model.BatchStock;
import com.concat.projetointegrador.model.Warehouse;
import com.concat.projetointegrador.repository.WarehouseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class WarehouseService {
    private WarehouseRepository warehouseRepository;

    /**
     * Search warehouse by id
     * @param id
     * @return a warehouse if found
     */
    public WarehouseDTO findById(Long id) { //usado
        Optional<Warehouse> warehouse = warehouseRepository.findById(id);

        if(warehouse.isEmpty()) {
            throw new EntityNotFound("Armazém não encontrado!");
        }
        return WarehouseDTO.convertToWarehouseDTO(warehouse.get());
    }

    /**
     * Search a products list by warehouse
     * @param batchProducts
     * @param inboundOrderService
     * @returns a list of products by warehouse
     */
    public List<WarehouseQuantityProductDTO> findAllProductForWarehouse(List<BatchStock> batchProducts, InboundOrderService inboundOrderService) {
        List<WarehouseQuantityProductDTO> warehouseQuantityProductDTOList = new ArrayList<>();
        List<WarehouseQuantityProductDTO> warehouseQuantityProductDTOs = batchProducts.stream().map(batchStock -> WarehouseQuantityProductDTO.map(
                batchStock.getCurrentQuantity(),
                inboundOrderService.findById(batchStock.getInboundOrder().getId()).getSector().getWarehouse().getId()
        )).collect(Collectors.toList());
        Map<Long, Integer> sumQuantityForWarehouse = WarehouseResponseForQuantityProductsDTO.sumQuantityForWarehouse(warehouseQuantityProductDTOs);

        sumQuantityForWarehouse.forEach((k,v) -> {
            warehouseQuantityProductDTOList.add(WarehouseQuantityProductDTO.builder().warehouseCode(k).totalQuantity(v).build());
        });

        return warehouseQuantityProductDTOList;
    }

    /**
     * Save a warehouse
     * @param warehouseModel - warehouse object to insert
     * @return a warehouse DTO
     */
    @Transactional
    public WarehouseDTO create(Warehouse warehouseModel) {
            Optional<Warehouse> warehouse = warehouseRepository.findByName(warehouseModel.getName());

        if(warehouse.isPresent()){
            throw new RuntimeException("Esse armazém já existe!");
        }

        return WarehouseDTO.convertToWarehouseDTO(warehouseRepository.save(warehouseModel));
    }

}
