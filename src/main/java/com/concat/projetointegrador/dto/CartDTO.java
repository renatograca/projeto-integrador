package com.concat.projetointegrador.dto;

import com.concat.projetointegrador.model.Product;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {

    private Product product;

    private Integer quantity;
}
