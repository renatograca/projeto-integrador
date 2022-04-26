package com.concat.projetointegrador.dto;

import com.concat.projetointegrador.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDTO {

    private Long id;

    public ProductRequestDTO map(Product product) {
        return new ProductRequestDTO(product.getId());
    }
}
