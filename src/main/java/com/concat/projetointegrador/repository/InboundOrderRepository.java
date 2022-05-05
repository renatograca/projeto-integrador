package com.concat.projetointegrador.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.concat.projetointegrador.model.InboundOrder;

@Repository
public interface InboundOrderRepository extends JpaRepository<InboundOrder, Long> {

}
