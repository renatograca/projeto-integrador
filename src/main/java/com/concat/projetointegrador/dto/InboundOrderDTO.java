package com.concat.projetointegrador.dto;

import java.time.LocalDate;
import java.util.List;

import com.concat.projetointegrador.model.BatchStock;
import com.concat.projetointegrador.model.InboundOrder;
import com.concat.projetointegrador.model.Sector;
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

    private Sector sector;

    private List<BatchStock> batchStock;

    public static InboundOrderDTO map (InboundOrder order) {
        return InboundOrderDTO
                .builder()
                .orderNumber(order.getId())
                .active(order.isActive())
                .sector(order.getSector())
                .batchStock(order.getBatchStock())
                .build();
    }

    public static InboundOrder map (InboundOrderDTO dto) {
        return InboundOrder
                .builder()
                .id(dto.getOrderNumber())
                .active(dto.isActive())
                .sector(dto.getSector())
                .batchStock(dto.getBatchStock())
                .build();
    }

}