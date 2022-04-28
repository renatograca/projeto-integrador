package com.concat.projetointegrador.dto;

import com.concat.projetointegrador.model.Cart;
import com.concat.projetointegrador.model.Product;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartResponseDTO {

    private List<CartDTO> carts;


}
