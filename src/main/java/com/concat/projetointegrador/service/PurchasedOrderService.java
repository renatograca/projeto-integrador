package com.concat.projetointegrador.service;

import com.concat.projetointegrador.dto.PurchasedOrderDTO;
import com.concat.projetointegrador.model.BatchStock;
import com.concat.projetointegrador.model.PurchasedOrder;
import com.concat.projetointegrador.repository.PurchasedOrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PurchasedOrderService {

    private PurchasedOrderRepository purchasedOrderRepository;
    private ProductService productService;
    private BatchStockService batchStockService;

    public PurchasedOrderDTO create(PurchasedOrder purchasedOrder) {
        purchasedOrder.getCart().stream()
                .forEach(cart -> batchStockService.findByProductId(cart.getProducts().getId(), cart.getQuantity()));

        BigDecimal total = purchasedOrder.getCart().stream()
                .reduce(BigDecimal.ZERO, (acc, cart) -> (acc.add(productService.findById(cart.getProducts().getId()).getPrice()
                        .multiply(BigDecimal.valueOf(cart.getQuantity())))),BigDecimal::add );

        PurchasedOrderDTO purchasedOrderDTO = PurchasedOrderDTO.builder().price(total).build();
        purchasedOrderRepository.save(purchasedOrder);
        return purchasedOrderDTO;
    }

}
