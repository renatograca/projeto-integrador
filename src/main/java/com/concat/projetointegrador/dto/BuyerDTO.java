package com.concat.projetointegrador.dto;

import com.concat.projetointegrador.model.Buyer;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BuyerDTO {

    private String name;
    private String lastName;
    private String username;
    private Long cpf;

    public static BuyerDTO convertToBuyerDTO(Buyer buyer) {
        return BuyerDTO.builder()
                .name(buyer.getName())
                .lastName(buyer.getLastName())
                .username(buyer.getUsername())
                .cpf(buyer.getCpf())
                .build();
    }
}
