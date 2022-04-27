package com.concat.projetointegrador.service;

import com.concat.projetointegrador.dto.PurchasedOrderDTO;
import com.concat.projetointegrador.model.PurchasedOrder;
import com.concat.projetointegrador.repository.PurchasedOrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class PurchasedOrderService {

    private PurchasedOrderRepository purchasedOrderRepository;
    private ProductService productService;

    public PurchasedOrderDTO create(PurchasedOrder purchasedOrder) {
        BigDecimal total = purchasedOrder.getCart().stream()
                .reduce(BigDecimal.ZERO, (acc, cart) -> (acc.add(productService.findById(cart.getProducts().getId()).getPrice()
                        .multiply(BigDecimal.valueOf(cart.getQuantity())))),BigDecimal::add );
        PurchasedOrderDTO purchasedOrderDTO = PurchasedOrderDTO.builder().price(total).build();
        purchasedOrderRepository.save(purchasedOrder);
        return purchasedOrderDTO;
    }

}
