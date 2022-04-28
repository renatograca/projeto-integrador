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
import java.util.*;
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
        List<Cart> carts = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;
        purchasedOrder = purchasedOrderRepository.save(purchasedOrder);
        for (Cart cart : purchasedOrder.getCart()) {

            List<BatchStock> batchStock = batchStockService.findByProductId(cart.getProducts().getId(), cart.getQuantity());
            total = total.add(batchStock.get(0).getProduct().getPrice().multiply(BigDecimal.valueOf(cart.getQuantity())));
            carts.add(Cart.builder().products(batchStock.get(0).getProduct()).quantity(cart.getQuantity())
                    .purchasedOrder(purchasedOrder).build());
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

    public PurchasedOrder update(Long id) {
        Optional<PurchasedOrder> purchasedOrder = purchasedOrderRepository.findById(id);
        if (!purchasedOrder.isPresent()) {
            throw new EntityNotFound("O pedido não existe");
        }
        if (!purchasedOrder.get().getStatus().equals("aberto")) {
            throw new RuntimeException("Este pedido já está finalizado");
        }

        Map<Long, Integer> map = new HashMap<>();

        for (Cart cart : purchasedOrder.get().getCart()) {
            List<BatchStock> batchStocks = batchStockService.findAllByProductId(cart.getProducts().getId(), "F");
            Integer productQuantityTotal = batchStocks.stream().
                    reduce(0, (acc, e) -> acc + e.getCurrentQuantity(), Integer::sum);

            if (productQuantityTotal < cart.getQuantity()) {
                throw new RuntimeException("A quantidade do produto não é suficiente");
            }

            for (BatchStock batchStock : batchStocks) {
                cart.getQuantity() - batchStock.getCurrentQuantity()
            }
        }

            for (Cart cart : purchasedOrder.get().getCart()) {
                List<BatchStock> batchStocks = batchStockService.findAllByProductId(cart.getProducts().getId(), "F");
                i
            }

            purchasedOrder.get().getCart().stream().forEach(e -> batchStockService.findByProductId(e.getProducts()
                    .getId(), e.getQuantity()));

        purchasedOrder.get().setStatus("finalizado");
        return purchasedOrderRepository.save(purchasedOrder.get());


    }
}
