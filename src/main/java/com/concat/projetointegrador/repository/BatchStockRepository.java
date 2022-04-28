package com.concat.projetointegrador.repository;

import com.concat.projetointegrador.model.BatchStock;

import java.util.List;
import java.util.Optional;

import com.concat.projetointegrador.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BatchStockRepository extends JpaRepository<BatchStock, Long> {

    Optional<BatchStock> findByProductId(Long id);
	  List<BatchStock> findAllByInboundOrderSectorId(Long id);

	  List<BatchStock> findAllByProductId(Long id);
}
