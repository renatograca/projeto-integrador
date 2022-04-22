package com.concat.projetointegrador.repository;

import com.concat.projetointegrador.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository <Product,String>{


}
