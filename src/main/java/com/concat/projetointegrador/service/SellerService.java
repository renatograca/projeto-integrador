package com.concat.projetointegrador.service;

import com.concat.projetointegrador.model.Seller;
import com.concat.projetointegrador.repository.SellerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SellerService implements ISellerService{

    private SellerRepository sellerRepository;

    @Override
    public Seller createSeller(Seller seller) {

        Seller newSeller = sellerRepository.save(seller);

        return newSeller;

    }

    @Override
    public List<Seller> getSellers() {

        List<Seller> sellers = sellerRepository.findAll();

        return sellers;

    }

    @Override
    public Seller getSellerByID(Long id) {
        return null;
    }

    @Override
    public Seller updateSeller(Seller seller) {
        return null;
    }

    @Override
    public void deleteSellerByID(Long id) {

    }
}
