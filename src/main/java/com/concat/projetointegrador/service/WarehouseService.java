package com.concat.projetointegrador.service;

import com.concat.projetointegrador.dto.WarehouseDTO;
import com.concat.projetointegrador.exception.EntityNotFound;
import com.concat.projetointegrador.model.Warehouse;
import com.concat.projetointegrador.repository.WarehouseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.List;

@Service
@AllArgsConstructor
public class WarehouseService {
    private WarehouseRepository warehouseRepository;

    public WarehouseDTO findById(Long id) {
        Optional<Warehouse> warehouse = warehouseRepository.findById(id);

        if(warehouse.isEmpty()) {
            throw new RuntimeException("Armazém não encontrado!");
        }

        return WarehouseDTO.convertToWarehouseDTO(warehouse.get());
    }

    public List<WarehouseDTO> findAll() {
        List<Warehouse> listWarehouse = warehouseRepository.findAll();

        if(listWarehouse.isEmpty()) {
            throw new RuntimeException("Não existem armazéns registados!");
        }

        return WarehouseDTO.convertToListWarehouse(listWarehouse);
    }

    @Transactional
    public WarehouseDTO create(Warehouse warehouseModel) {
            Optional<Warehouse> warehouse = warehouseRepository.findByName(warehouseModel.getName());

        if(warehouse.isPresent()){
            throw new RuntimeException("Esse armazém já esta cadastrado!");
        }

        return WarehouseDTO.convertToWarehouseDTO(warehouseRepository.save(warehouseModel));
    }

    public WarehouseDTO update(Warehouse warehouseModel, Long id) {
        Warehouse warehouse = warehouseRepository.findById(id).orElseThrow(() -> new EntityNotFound("Este armazém não existe!"));

        warehouse.setName(warehouseModel.getName());
        warehouse.setRegiao(warehouseModel.getRegiao());

        warehouseRepository.save(warehouse);

        return WarehouseDTO.convertToWarehouseDTO(warehouseRepository.save(warehouse));
    }

    public void delete(Long id) {
        warehouseRepository.deleteById(id);
    }
}
