package com.concat.projetointegrador.dto;

import java.time.LocalDate;
import java.util.List;

import com.concat.projetointegrador.model.BatchStock;
import com.concat.projetointegrador.model.InboundOrder;
import com.concat.projetointegrador.model.Sector;
import com.concat.projetointegrador.service.SectorService;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(value={ "orderNumber", "active", "orderDate" }, allowSetters= true)
public class InboundOrderDTO {

    private Long orderNumber;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private final LocalDate orderDate = LocalDate.now();

    private boolean active;

    private SectorRequestDTO sector;

    private List<BatchStock> batchStock;

    public static InboundOrderDTO map (InboundOrder order) {
        SectorRequestDTO sectorRequestDTO = new SectorRequestDTO(order.getSector().getId(),
                order.getSector().getWarehouse().getId());
        return InboundOrderDTO
                .builder()
                .orderNumber(order.getId())
                .active(order.isActive())
                .sector(sectorRequestDTO)
                .batchStock(order.getBatchStock())
                .build();
    }

    public static InboundOrder map (InboundOrderDTO dto, Sector sector) {

        return InboundOrder
                .builder()
                .id(dto.getOrderNumber())
                .active(dto.isActive())
                .sector(sector)
                .batchStock(dto.getBatchStock())
                .build();
    }

}