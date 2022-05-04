package com.concat.projetointegrador.service;

import com.concat.projetointegrador.dto.ProductDTO;
import com.concat.projetointegrador.exception.EntityNotFound;
import com.concat.projetointegrador.model.Category;
import com.concat.projetointegrador.model.Product;
import com.concat.projetointegrador.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepository;
    private SellerService sellerService;


    /**
     * find a product by its id
     * @param id - product id
     * @return - product if it exists
     * @throws - EntityNotFound if product doenst exist
     */
    public Product findById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()){
            return product.get();
        } else {
            throw new EntityNotFound("O produto não foi encontrado!!");
        }
    }

    /**
     * find all products
     * @return - a list of DTO
     * @throws - EntityNotFound if product is not registered
     */
    public List<ProductDTO> findAll() {
        List<Product> listProduct = productRepository.findAll();

        if (listProduct.isEmpty()) {
            throw new EntityNotFound("Não existem produtos cadastrados.");
        }
        List<ProductDTO> listDTO = ProductDTO.convertToListProductDTO(listProduct);
        return listDTO;
    }


    /**
     * create a new product
     * @param product - an object with data to register in the database
     * @return - returns the created database
     * @throws - RunTimeException if product is exist
     */
    public ProductDTO create(Product product) {
        Optional<Product> productOpt = productRepository.findById(product.getId());
        if (productOpt.isPresent()) {
            throw new RuntimeException("Esse produto já existe!");
        }
        product.setSeller(sellerService.findByID(product.getSeller().getId()));
        ProductDTO productDTO = ProductDTO.convertToProductDTO(productRepository.save(product));
        return productDTO;

    }

    /**
     * find a product by its category
     * @param category - product category
     * @return a list of product DTO
     * @throws - EntityNotFound if product is not registered
     */
    public List<ProductDTO> findByCategory(Category category) {
        List<Product> products = productRepository.findByCategory(category);
        if(products.isEmpty()) {
            throw new EntityNotFound("Não existem produtos nesta categoria!");
        }
        List<ProductDTO> productsDTO = ProductDTO.convertToListProductDTO(products);
        return productsDTO;
    }
}
