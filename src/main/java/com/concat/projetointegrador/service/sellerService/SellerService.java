package com.concat.projetointegrador.service.sellerService;

import com.concat.projetointegrador.exception.advice.sellerExceptions.NoSellerException;
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
    public Seller create(Seller seller) {

        Seller newSeller = sellerRepository.save(seller);

        return newSeller;

    }

    @Override
    public List<Seller> findAll() {

        List<Seller> sellers = sellerRepository.findAll();

        if(sellers.isEmpty()){

            throw new NoSellerException("Seller não existe.");

        }

        return sellers;

    }

    @Override
    public Optional<Seller> findByID(Long id) {

        Optional<Seller> seller = sellerRepository.findById(id);

        if(seller.isEmpty()){

            throw new NoSellerException("Seller não existe.");

        }

        return seller;

    }

    @Override
    public Seller update(Seller seller, Long id) {

        Optional<Seller> doesTheSellerExist = sellerRepository.findById(id);

        if(doesTheSellerExist.isEmpty()){

            throw new NoSellerException("Seller não existe.");

        }

        Seller updatedSeller = sellerRepository.save(seller);

        return updatedSeller;

    }

    @Override
    public void deleteByID(Long id) {

        sellerRepository.deleteById(id);

    }
}
