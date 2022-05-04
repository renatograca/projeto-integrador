package com.concat.projetointegrador.dto;

import com.concat.projetointegrador.model.Product;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartsDTO {

    private Long id;
    private Integer quantity;
    private ProductDTO productDTO;// corrigir esse nome

}
