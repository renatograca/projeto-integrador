package com.concat.projetointegrador.service;

import com.concat.projetointegrador.dto.ProductDTO;
import com.concat.projetointegrador.exception.EntityNotFound;
import com.concat.projetointegrador.model.Product;
import com.concat.projetointegrador.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepository;

    public Product findById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()){
            return product.get();
        } else {
            throw new EntityNotFound("O produto não foi encontrado!!");
        }
    }

    public List<ProductDTO> findAll() {
        List<Product> listProduct = productRepository.findAll();
        List<ProductDTO> listDTO = ProductDTO.convertToListProductDTO(listProduct);
        return listDTO;
    }


    public ProductDTO create(Product product) {
        Optional<Product> productOpt = productRepository.findByName(product.getName());
        if (productOpt.isPresent()) {
            throw new EntityNotFound("Esse produto já existe!"); // criar uma classe de erro especificaa
        } else {
            ProductDTO productDTO = ProductDTO.convertToProductDTO(productRepository.save(product));
            return productDTO;
        }
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    public ProductDTO update(Product product, Long id) {
        Optional<Product> productRepo = productRepository.findById(id);
        if (productRepo.isPresent()) {
            productRepo.get().setCategory(product.getCategory());
            productRepo.get().setName(product.getName());
            productRepo.get().setVolume(product.getVolume());
            productRepo.get().setPrice(product.getPrice());
            ProductDTO productDTO = ProductDTO.convertToProductDTO(productRepository.save(productRepo.get()));
            return productDTO;
        } else {
            throw new EntityNotFound("O produto não existe!!");
        }
    }
}
