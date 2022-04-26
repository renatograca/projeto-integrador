package com.concat.projetointegrador.dto;

import com.concat.projetointegrador.model.Category;
import com.concat.projetointegrador.model.Product;
import lombok.*;

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
    private LocalDate dueDate;
    private Integer size;
    private Category category;

    public static ProductDTO convertToProductDTO(Product product){

        return ProductDTO.builder().category(product.getCategory()).dueDate(product.getDueDate())
                .name(product.getName()).size(product.getSize()).build();

    }

    public static List<ProductDTO> convertToListProduct (List<Product> listProduct){

        return listProduct.stream().map(product -> new ProductDTO(product.getName(), product.getDueDate(),
                product.getSize(), product.getCategory())).collect(Collectors.toList());

    }

}
