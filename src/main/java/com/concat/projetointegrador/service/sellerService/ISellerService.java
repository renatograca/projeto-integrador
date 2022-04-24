package com.concat.projetointegrador.service.sellerService;

import com.concat.projetointegrador.model.sellerModel.Seller;

import java.util.List;
import java.util.Optional;

public interface ISellerService {

    public Seller createSeller(Seller seller);
    public List<Seller> getSellers();
    public Optional<Seller> getSellerByID(Long id);
    public Seller updateSeller(Seller seller);
    public void deleteSellerByID(Long id);

}
