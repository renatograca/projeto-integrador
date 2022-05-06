package com.concat.projetointegrador.service;

import com.concat.projetointegrador.dto.ProductDTO;
import com.concat.projetointegrador.exception.EntityNotFound;
import com.concat.projetointegrador.model.BatchStock;
import com.concat.projetointegrador.model.Category;
import com.concat.projetointegrador.model.Product;
import com.concat.projetointegrador.repository.BatchStockRepository;
import com.concat.projetointegrador.repository.ProductRepository;
import com.concat.projetointegrador.service.util.ProductsWithDiscount;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.concat.projetointegrador.service.util.ProductOfBatchStockHasADiscountDueDate.differenceBetweenDates;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepository;

    private BatchStockRepository batchStockRepository;
    private SellerService sellerService;

    /**
     * Search for a product by id
     * @param id Long - product id
     * @return a product if registered
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
     * Search a product list
     * @return a list of registered products
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
     * Save a product
     * @param product - product object to insert
     * @return a product DTO
     */
    public ProductDTO create(Product product) {
        product.setSeller(sellerService.findByID(product.getSeller().getId()));
        ProductDTO productDTO = ProductDTO.convertToProductDTO(productRepository.save(product));
        return productDTO;
    }

    /**
     * Search a list of products by category
     * @param category - Enum category
     * @return a list of products DTO
     */
    public List<ProductDTO> findByCategory(Category category) {
        List<Product> products = productRepository.findByCategory(category);
        if(products.isEmpty()) {
            throw new EntityNotFound("Não existem produtos nesta categoria!");
        }
        List<ProductDTO> productsDTO = ProductDTO.convertToListProductDTO(products);
        return productsDTO;
    }

    public List<BatchStock> findAllProductsWithDiscount() {
        List<BatchStock> batchStocks = batchStockRepository.findAll();
       if(batchStocks.isEmpty()) {
           throw new EntityNotFound("Não a produtos com desconto");
       }
        List<BatchStock> batchStocksWithDiscount = batchStocks.stream().filter(batchStock ->
            differenceBetweenDates(LocalDate.now(), batchStock.getDueDate()) <= 30).collect(Collectors.toList());
        return batchStocksWithDiscount;
    }
}
