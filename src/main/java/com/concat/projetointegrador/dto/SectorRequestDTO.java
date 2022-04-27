package com.concat.projetointegrador.dto;

import com.concat.projetointegrador.model.Sector;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SectorRequestDTO {

    private Long sectorCode;
    private Long warehouseCode;

    public static SectorRequestDTO map(Sector sector) {
        return SectorRequestDTO
                .builder()
                .sectorCode(sector.getId())
                .warehouseCode(sector.getWarehouse().getId())
                .build();
    }
}

