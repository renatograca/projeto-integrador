package com.concat.projetointegrador.repository;

import com.concat.projetointegrador.model.WarehouseModel;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WarehouseRepository extends JpaRepository<WarehouseModel, Long> {
    Optional<WarehouseModel> findByName(String name);
}