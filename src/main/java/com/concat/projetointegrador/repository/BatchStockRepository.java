package com.concat.projetointegrador.repository;

import com.concat.projetointegrador.model.BatchStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BatchStockRepository extends JpaRepository<BatchStock, Long> {

    Optional<BatchStock> findByProductId(Long id);
}
