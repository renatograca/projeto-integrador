package com.concat.projetointegrador.dto;

import java.time.LocalDate;

import com.concat.projetointegrador.model.InboundOrder;
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

    //todo trocar  tipo
    private String section;

    //todo trocar tipo
    private String batchStock;

    public static InboundOrderDTO map (InboundOrder order) {
        return InboundOrderDTO
                .builder()
                .orderNumber(order.getId())
                .active(order.isActive())
                .section(order.getSection())
                .batchStock(order.getBatchStock())
                .build();
    }

    public static InboundOrder map (InboundOrderDTO dto) {
        return InboundOrder
                .builder()
                .id(dto.getOrderNumber())
                .active(dto.isActive())
                .section(dto.getSection())
                .batchStock(dto.getBatchStock())
                .build();
    }

}