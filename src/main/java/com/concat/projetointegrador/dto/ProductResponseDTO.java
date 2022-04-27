package com.concat.projetointegrador.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDTO {

    private Long productId;
    private SectorRequestDTO sector;
    private List<BatchStockDTO> batchStock;

}
