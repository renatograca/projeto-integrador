package com.concat.projetointegrador.service;

import com.concat.projetointegrador.dto.CartDTO;
import com.concat.projetointegrador.dto.PurchasedOrderDTO;
import com.concat.projetointegrador.exception.EntityNotFound;
import com.concat.projetointegrador.model.BatchStock;
import com.concat.projetointegrador.model.Cart;
import com.concat.projetointegrador.model.PurchasedOrder;
import com.concat.projetointegrador.repository.CartRepository;
import com.concat.projetointegrador.repository.PurchasedOrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PurchasedOrderService {

    private PurchasedOrderRepository purchasedOrderRepository;
    private ProductService productService;
    private BatchStockService batchStockService;
    private CartRepository cartRepository;
    private BuyerService buyerService;

    public PurchasedOrderDTO create(PurchasedOrder purchasedOrder) {
        buyerService.findById(purchasedOrder.getBuyer().getId());
        List<Cart> carts =  new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;
        purchasedOrder = purchasedOrderRepository.save(purchasedOrder);
        for (Cart cart: purchasedOrder.getCart()) {

            BatchStock batchStock = batchStockService.findByProductId(cart.getProducts().getId(), cart.getQuantity());
            carts.add(Cart.builder().products(batchStock.getProduct()).quantity(cart.getQuantity()).purchasedOrder(purchasedOrder).build());
            total = total.add(batchStock.getProduct().getPrice().multiply(BigDecimal.valueOf(cart.getQuantity())));
        }
        carts = cartRepository.saveAll(carts);
        purchasedOrder.setCart(carts);
        PurchasedOrderDTO purchasedOrderDTO = PurchasedOrderDTO.builder().price(total).build();
        purchasedOrderRepository.save(purchasedOrder);
        return purchasedOrderDTO;
    }

    public PurchasedOrder findById(Long id) {
        Optional<PurchasedOrder> purchasedOrderOpt = purchasedOrderRepository.findById(id);
        if (purchasedOrderOpt.isPresent()) {
            return purchasedOrderOpt.get();
        }
        throw new EntityNotFound("Pedido não existe");
    }
}
