package com.concat.projetointegrador.dto;

import com.concat.projetointegrador.model.BatchStock;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

import static com.concat.projetointegrador.service.util.ProductOfBatchStockHasADiscountDueDate.verifyDiscount;

@Data
@Builder
public class PurchasedDiscountDTO {
    private String product;
    private BigDecimal price;
    private BigDecimal priceWithDiscount;
    private Integer quantity;

    public static PurchasedDiscountDTO map(BatchStock batchStock) {
        return PurchasedDiscountDTO.builder()
                .product(batchStock.getProduct().getName())
                .price(batchStock.getProduct().getPrice())
                .priceWithDiscount(verifyDiscount(batchStock))
                .quantity(batchStock.getCurrentQuantity())
                .build();
    }
}
