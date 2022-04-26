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

    public ProductDTO findById(Long id) {

        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()){
            ProductDTO productDTO = ProductDTO.convertToProductDTO(product.get());
            return productDTO;
        } else {
            throw new EntityNotFound("O produto não foi encontrado!!");
        }

    }

    public List<ProductDTO> findAll() {
        List<Product> listProduct = productRepository.findAll();

        List<ProductDTO> listDTO = ProductDTO.convertToListProduct(listProduct);
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
            productRepo.get().setDueDate(product.getDueDate());
            productRepo.get().setCurrentQuality(product.getCurrentQuality());
            productRepo.get().setCurrentTemperature(product.getCurrentTemperature());
            productRepo.get().setName(product.getName());
            productRepo.get().setManufacturingTime(product.getManufacturingTime());
            productRepo.get().setInitialQuality(product.getInitialQuality());
            productRepo.get().setSize(product.getSize());
            productRepo.get().setInitialTemperature(product.getInitialTemperature());
            productRepo.get().setManufacturingDate(product.getManufacturingDate());

            ProductDTO productDTO = ProductDTO.convertToProductDTO(productRepository.save(productRepo.get()));
            return productDTO;

        } else {
            throw new EntityNotFound("O produto não existe!!");
        }

    }

}
