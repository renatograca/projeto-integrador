package com.concat.projetointegrador.service.sellerService;

import com.concat.projetointegrador.model.sellerModel.Seller;

import java.util.List;
import java.util.Optional;

public interface ISellerService {

    public Seller create(Seller seller);
    public List<Seller> findAll();
    public Optional<Seller> findByID(Long id);
    public Seller updateByID(Seller seller, Long id);
    public void deleteByID(Long id);

}
