package com.concat.projetointegrador.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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
public class InboundOrderDTO {

    private Long orderNumber;

    private final LocalDate orderDate = LocalDate.now();

    private boolean active;

    private SectorRequestDTO sector;

    private List<BatchStockDTO> batchStock;

    public static InboundOrderDTO map (InboundOrder order) {
        SectorRequestDTO sectorRequestDTO = new SectorRequestDTO(order.getSector().getId(),
                order.getSector().getWarehouse().getId());
        return InboundOrderDTO
                .builder()
                .orderNumber(order.getId())
                .active(order.isActive())
                .sector(sectorRequestDTO)
                .batchStock(order.getBatchStock().stream().map(batchStock1 -> BatchStockDTO.map(batchStock1)).collect(Collectors.toList()))
                .build();
    }

    public static InboundOrder map (InboundOrderDTO dto, Sector sector, List<BatchStock> batchStocks) {

        return InboundOrder
                .builder()
                .id(dto.getOrderNumber())
                .active(dto.isActive())
                .sector(sector)
                .batchStock(batchStocks)
                .build();
    }

}