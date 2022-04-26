package com.concat.projetointegrador.repository.sellerRepository;

import com.concat.projetointegrador.model.sellerModel.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {
}
