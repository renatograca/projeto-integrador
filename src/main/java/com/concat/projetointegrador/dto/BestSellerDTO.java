package com.concat.projetointegrador.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BestSellerDTO {

    private Long id;
    private String username;
    private Integer quantityOfProductsSale;

}
