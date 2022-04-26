package com.concat.projetointegrador.service;

import com.concat.projetointegrador.dto.ProductDTO;
import com.concat.projetointegrador.model.Category;
import com.concat.projetointegrador.model.Product;
import com.concat.projetointegrador.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {


    private ProductRepository productRepository;

    public ProductDTO findById(Long id) {
        
        Optional<Product> product = productRepository.findById(id);
        ProductDTO productDTO = ProductDTO.convertToProductDTO(product.get());
        return productDTO;
        
    }

    public List<ProductDTO> findAll() {
        List<Product> listProduct = productRepository.findAll();
        
        List<ProductDTO> listDTO = ProductDTO.convertToListProduct(listProduct);
        return listDTO;
    }


    public ProductDTO create(Product product) {
        
        ProductDTO productDTO = ProductDTO.convertToProductDTO(productRepository.save(product));
        return productDTO;
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }


    public ProductDTO update(Product product, Long id) {
        Product productRepo = productRepository.findById(id).orElse(new Product());

       productRepo.setCategory(product.getCategory());
       productRepo.setDueDate(product.getDueDate());
       productRepo.setCurrentQuality(product.getCurrentQuality());
       productRepo.setCurrentTemperature(product.getCurrentTemperature());
       productRepo.setName(product.getName());
       productRepo.setManuFacturingTime(product.getManuFacturingTime());
       productRepo.setInitialQuality(product.getInitialQuality());
       productRepo.setSize(product.getSize());
       productRepo.setInitialTemperature(product.getInitialTemperature());
       productRepo.setManuFacturingDate(product.getManuFacturingDate());

       productRepository.save(productRepo);

       ProductDTO productDTO = ProductDTO.convertToProductDTO(productRepository.save(productRepo));
       return productDTO;
    }
}
