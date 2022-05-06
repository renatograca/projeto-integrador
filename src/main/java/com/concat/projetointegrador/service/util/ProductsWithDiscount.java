package com.concat.projetointegrador.service.util;

import com.concat.projetointegrador.model.BatchStock;

import java.time.LocalDate;

import static com.concat.projetointegrador.service.util.ProductOfBatchStockHasADiscountDueDate.differenceBetweenDates;
public class ProductsWithDiscount {
    public static BatchStock products(BatchStock batchStock) {
        Long dueDays = differenceBetweenDates(LocalDate.now(), batchStock.getDueDate());
        if (dueDays <= 30 ) {
            return batchStock;
        }
        return new BatchStock();
    }
}
