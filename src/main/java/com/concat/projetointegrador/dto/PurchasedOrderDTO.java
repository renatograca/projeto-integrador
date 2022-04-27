package com.concat.projetointegrador.dto;

import lombok.*;

import java.math.BigDecimal;

@Builder
@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
public class PurchasedOrderDTO {

    private BigDecimal price;
}
