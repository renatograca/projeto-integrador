package com.concat.projetointegrador.repository;

import com.concat.projetointegrador.model.Seller;
import com.concat.projetointegrador.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {

}
