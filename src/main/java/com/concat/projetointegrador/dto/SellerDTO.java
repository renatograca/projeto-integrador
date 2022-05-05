package com.concat.projetointegrador.dto;

import com.concat.projetointegrador.model.Seller;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SellerDTO {

    private Long id;
    private String name;
    private String lastName;
    private String username;
    private Long cpf;

    public static SellerDTO convertToSellerDTO(Seller seller) {
        return SellerDTO.builder()
                .id(seller.getId())
                .name(seller.getName())
                .lastName(seller.getLastName())
                .username(seller.getUsername())
                .cpf(seller.getCpf())
                .build();
    }
}
