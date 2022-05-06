package com.concat.projetointegrador.service.util;

import com.concat.projetointegrador.model.BatchStock;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class ProductOfBatchStockHasADiscountDueDate {

    /**
     * set price of product with discount
     */
    public static BigDecimal verifyDiscount(BatchStock batchStock) {
        Long dueDays = differenceBetweenDates(LocalDate.now(), batchStock.getDueDate());
        if (dueDays <= 30 && dueDays >= 21) {
            return discountCalculation(batchStock.getProduct().getPrice(), 0.1);
        }
        if (dueDays <= 20 && dueDays >= 11) {
            return discountCalculation(batchStock.getProduct().getPrice(), 0.2);
        }
        if (dueDays <= 10) {
            return discountCalculation(batchStock.getProduct().getPrice(), 0.3);
        }
        return batchStock.getProduct().getPrice();
    }

    /**
     * @param price
     * @param discount Example: To calculate 10% discount pass the value Double 0.1
     * @return BigDecimal returns the discounted amount
     */
    private static BigDecimal discountCalculation(BigDecimal price, Double discount) {
        BigDecimal mutiply = BigDecimal.valueOf(discount);
        BigDecimal priceSubtractPorcent = price.subtract(price.multiply(mutiply));
        return priceSubtractPorcent;
    }

    /**
     * returns the difference in days between the dates
     *
     * @param startDate
     * @param endDate
     * @return Long number of days
     */
    public static Long differenceBetweenDates(LocalDate startDate, LocalDate endDate) {
        return endDate.toEpochDay() - startDate.toEpochDay();
    }
}
