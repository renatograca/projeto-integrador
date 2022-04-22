package com.concat.projetointegrador.service.sellerService;

import com.concat.projetointegrador.model.sellerModel.Seller;
import com.concat.projetointegrador.repository.sellerRepository.SellerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SellerService implements ISellerService {

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
    public Optional<Seller> getSellerByID(Long id) {

        Optional<Seller> seller = sellerRepository.findById(id);

        return seller;

    }

    @Override
    public Seller updateSeller(Seller seller) {

        Seller updatedSeller = sellerRepository.save(seller);

        return updatedSeller;

    }

    @Override
    public void deleteSellerByID(Long id) {

    }
}
