package com.concat.projetointegrador.service;

import com.concat.projetointegrador.exception.EntityNotFound;
import com.concat.projetointegrador.model.Seller;
import com.concat.projetointegrador.repository.SellerRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SellerService {

		private final BCryptPasswordEncoder passwordEncoder;
    private SellerRepository sellerRepository;

    public Seller findByID(Long id) {
        Optional<Seller> seller = sellerRepository.findById(id);
        if (seller.isPresent()) {
        return seller.get();
        }
            throw new EntityNotFound("Vendedor não existe.");
    }

		public Seller create(Seller seller) {
				seller.setPassword(passwordEncoder.encode(seller.getPassword()));
				return sellerRepository.save(seller);
		}
}
