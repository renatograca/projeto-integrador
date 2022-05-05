package com.concat.projetointegrador.dto;

import com.concat.projetointegrador.model.Seller;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

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

    public static List<SellerDTO> convertToListSellerDTO(List<Seller> listSeller){
        return listSeller.stream()
                .map(seller -> new SellerDTO(
                        seller.getId(),
                        seller.getName(),
                        seller.getLastName(),
                        seller.getUsername(),
                        seller.getCpf()))
                .collect(Collectors.toList());



    }
}
