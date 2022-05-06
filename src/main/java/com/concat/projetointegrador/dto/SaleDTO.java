package com.concat.projetointegrador.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaleDTO {

    private Long sellerID;
    private String username;
    private Integer salesQuantity;


}
