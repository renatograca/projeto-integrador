package com.concat.projetointegrador.service;

import com.concat.projetointegrador.exception.EntityNotFound;
import com.concat.projetointegrador.model.Seller;
import com.concat.projetointegrador.repository.SellerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class SellerService {

    private SellerRepository sellerRepository;

    /**
     * Find a Seller by ID or throw an EntityNotFound Exception
     * @param id A Long type ID from a seller
     * @return Seller
     */
    public Seller findByID(Long id) {
        Optional<Seller> seller = sellerRepository.findById(id);
        if (seller.isPresent()) {
        return seller.get();
        }
            throw new EntityNotFound("Vendedor não existe.");
    }
}
