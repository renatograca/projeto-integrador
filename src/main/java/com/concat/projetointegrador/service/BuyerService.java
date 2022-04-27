package com.concat.projetointegrador.service;

import com.concat.projetointegrador.exception.EntityNotFound;
import com.concat.projetointegrador.model.Buyer;
import com.concat.projetointegrador.repository.BuyerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BuyerService {

    private BuyerRepository buyerRepository;

    public Buyer create(Buyer buyer) {
        Optional<Buyer> buyerOpt = buyerRepository.findByCpf(buyer.getCpf());
        if(buyerOpt.isPresent()) {
            throw new RuntimeException("Comprador já cadastrado");
        }
        return buyerRepository.save(buyer);
    }

    public Buyer findById(Long id) {
        Optional<Buyer> buyer = buyerRepository.findById(id);
        if(buyer.isPresent()) {
        return buyer.get();
        }
        throw new EntityNotFound("Comprador não encontrado");
    }

    public List<Buyer> findAll() {
        return buyerRepository.findAll();
    }

    public void delete(Long id) {
        buyerRepository.deleteById(id);
    }

}
