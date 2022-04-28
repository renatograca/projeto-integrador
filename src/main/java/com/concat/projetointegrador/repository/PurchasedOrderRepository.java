package com.concat.projetointegrador.repository;
import com.concat.projetointegrador.model.PurchasedOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchasedOrderRepository extends JpaRepository<PurchasedOrder, Long> {
}
