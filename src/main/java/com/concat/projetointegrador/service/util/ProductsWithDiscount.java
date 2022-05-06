package com.concat.projetointegrador.service.util;

import com.concat.projetointegrador.model.BatchStock;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static com.concat.projetointegrador.service.util.ProductOfBatchStockHasADiscountDueDate.differenceBetweenDates;
public class ProductsWithDiscount {
    public static BatchStock products(BatchStock batchStock) {
        long dueDays = differenceBetweenDates(LocalDate.now(), batchStock.getDueDate());
        if (dueDays <= 30 && dueDays >= 21) {
            return batchStock;
        }
        if (dueDays <= 20 && dueDays >= 11) {
            return batchStock;
        }
        if (dueDays <= 10) {
            return batchStock;
        }
        return null;
    }
}
