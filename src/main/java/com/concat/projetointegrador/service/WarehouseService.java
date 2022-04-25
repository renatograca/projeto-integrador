package com.concat.projetointegrador.service;

import com.concat.projetointegrador.DTO.WarehouseDTO;
import com.concat.projetointegrador.model.WarehouseModel;
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
        Optional<WarehouseModel> byId = warehouseRepository.findById(id);

        if(byId.isEmpty()) {
            throw new RuntimeException("Warehouse não encontrado!");
        }

        WarehouseDTO warehouseDTO = WarehouseDTO.convertToWarehouseDTO(byId.orElse(new WarehouseModel()));
        return warehouseDTO;
    }

    public List<WarehouseDTO> findAll() {
        List<WarehouseModel> listWarehouse = warehouseRepository.findAll();

        if(listWarehouse.isEmpty() || null == listWarehouse) {
            throw new RuntimeException("Não existem warehouse registados!");
        }

        List<WarehouseDTO> listWarehouseDTO = WarehouseDTO.convertToListWarehouse(listWarehouse);
        return listWarehouseDTO;
    }

    @Transactional
    public WarehouseDTO create(WarehouseModel warehouseModel) {
            Optional<WarehouseModel> warehouse = warehouseRepository.findByName(warehouseModel.getName());

        if(warehouse.isPresent()){
            throw new RuntimeException("Esse warehouse já esta cadastrado!");
        }

        WarehouseDTO warehouseDTO = WarehouseDTO.convertToWarehouseDTO(warehouseRepository.save(warehouseModel));
        return warehouseDTO;
    }

    public WarehouseDTO update(WarehouseModel warehouseModel, Long id) {
        WarehouseModel warehouse = warehouseRepository.findById(id).orElse(new WarehouseModel());

        warehouse.setName(warehouseModel.getName());
        warehouse.setRegiao(warehouseModel.getRegiao());

        warehouseRepository.save(warehouse);

        WarehouseDTO warehouseDTO = WarehouseDTO.convertToWarehouseDTO(warehouseRepository.save(warehouse));
        return warehouseDTO;
    }

    public void delete(Long id) {
        warehouseRepository.deleteById(id);
    }
}
