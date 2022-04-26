package com.concat.projetointegrador.dto;

import com.concat.projetointegrador.model.Category;
import com.concat.projetointegrador.model.Product;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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

    public static List<ProductDTO> convertToListProduct (List<Product> listProduct){
        return listProduct.stream().map(product -> new ProductDTO(product.getName(), product.getVolume(),
                product.getPrice(), product.getCategory())).collect(Collectors.toList());
    }
}
