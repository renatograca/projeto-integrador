package com.concat.projetointegrador.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import com.concat.projetointegrador.model.Category;
import com.concat.projetointegrador.model.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private String name;
    private Integer volume;
    private BigDecimal price;
    private Category category;
    
    
    public static ProductDTO convertToProductDTO(Product product){
        return ProductDTO.builder()
                .category(product.getCategory())
                .name(product.getName())
                .price(product.getPrice())
                .volume(product.getVolume()).build();
    }

    public static List<ProductDTO> convertToListProductDTO (List<Product> listProduct){
        return listProduct.stream().map(product -> new ProductDTO(product.getName(), product.getVolume(),
                product.getPrice(), product.getCategory())).collect(Collectors.toList());
    }
}
