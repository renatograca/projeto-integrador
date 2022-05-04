package com.concat.projetointegrador.dto;

import com.concat.projetointegrador.model.Cart;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PurchasedOrderResponseDTO {

    private Long id;
    private LocalDate date;
    private String status;
    private List<CartsDTO> cartsDTO;

}
