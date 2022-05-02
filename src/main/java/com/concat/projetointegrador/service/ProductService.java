package com.concat.projetointegrador.service;

import com.concat.projetointegrador.dto.ProductDTO;
import com.concat.projetointegrador.exception.EntityNotFound;
import com.concat.projetointegrador.model.Category;
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
    private SellerService sellerService;

    /**
     * Busca um produto pelo id
     * @param id Long - id do produto
     * @return um produto caso esteja cadastrado
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
     * Busca um lista de produtos
     * @return uma lista de produtos cadastrado
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
     * Salva um produto
     * @param product - objeto produto para inserção
     * @return um produto DTO
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
     * Busca uma lista de produtos por categoria
     * @param category - Enum categoria
     * @return uma lista de produtos DTO
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
