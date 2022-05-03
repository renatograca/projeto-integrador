package com.concat.projetointegrador.service;

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
import java.util.concurrent.atomic.AtomicReference;

@Service
@AllArgsConstructor
public class PurchasedOrderService {

    private PurchasedOrderRepository purchasedOrderRepository;
    private BatchStockService batchStockService;
    private CartRepository cartRepository;
    private BuyerService buyerService;

    /**
     * Save a PurchasedOrder
     * @param purchasedOrder is an object PurchasedOrder to save
     * @return Sum of all products prices in cart, multiplied by yours quantity
     */
    public PurchasedOrderDTO create(PurchasedOrder purchasedOrder) {
        buyerService.findById(purchasedOrder.getBuyer().getId());
        List<Cart> carts = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;
        for (Cart cart : purchasedOrder.getCart()) {
            List<BatchStock> batchStock = batchStockService.findByProductId(cart.getProducts().getId(), cart.getQuantity());
            total = total.add(batchStock.get(0).getProduct().getPrice().multiply(BigDecimal.valueOf(cart.getQuantity())));
            carts.add(Cart.builder().products(batchStock.get(0).getProduct()).quantity(cart.getQuantity())
                    .purchasedOrder(purchasedOrder).build());
        }
        purchasedOrder = purchasedOrderRepository.save(purchasedOrder);
        carts = cartRepository.saveAll(carts);
        purchasedOrder.setCart(carts);
        PurchasedOrderDTO purchasedOrderDTO = PurchasedOrderDTO.builder().price(total).build();
        purchasedOrderRepository.save(purchasedOrder);
        return purchasedOrderDTO;
    }

    /**
     * Find a PurchaseOrder by id
     * @param id is a Long property on PurchasedOrder
     * @return a PurchasedOrder or an EntityNotFound Exception
     */
    public PurchasedOrder findById(Long id) {
        Optional<PurchasedOrder> purchasedOrderOpt = purchasedOrderRepository.findById(id);
        if (purchasedOrderOpt.isPresent()) {
            return purchasedOrderOpt.get();
        }
        throw new EntityNotFound("Pedido não existe");

    }

    /**
     * Change the status on a PurchasedOrder
     * @param id is a Long property on PurchasedOrder
     * @return the updated status on PurchasedOrder for "finalizado" or an custom exception
     */
    public PurchasedOrder update(Long id) {
        Optional<PurchasedOrder> purchasedOrder = purchasedOrderRepository.findById(id);
        if (!purchasedOrder.isPresent()) {
            throw new EntityNotFound("O pedido não existe");
        }
        if (!purchasedOrder.get().getStatus().equals("aberto")) {
            throw new RuntimeException("Este pedido já está finalizado");
        }



        validateQuantityInBatchStock(purchasedOrder.get());
        purchasedOrder.get().setStatus("finalizado");
        PurchasedOrder purchasedOrderDB = purchasedOrderRepository.save(purchasedOrder.get());


                purchasedOrder.get().getCart().forEach(cart -> {
                    List<BatchStock> batchStocks = batchStockService.findAllByProductId(cart.getProducts().getId(), "F");
                    AtomicReference<Integer> quantity = new AtomicReference<>(cart.getQuantity());
                    batchStocks.forEach(batchStock -> {
                        if (batchStock.getCurrentQuantity() >= quantity.get()) {
                            batchStock.setCurrentQuantity(batchStock.getCurrentQuantity() - quantity.get());
                            quantity.set(0);
                        } else {
                            quantity.set(quantity.get() - batchStock.getCurrentQuantity());
                            batchStock.setCurrentQuantity(0);
                        }
                        batchStockService.create(batchStock);
                    });
                });


        return purchasedOrderDB;

    }

    /**
     * Validate if BatchStock has a valid quantity
     * @param purchasedOrder is an object PurchasedOrder with the BatchStock to validate
     */
    private void validateQuantityInBatchStock(PurchasedOrder purchasedOrder) {
        for (Cart cart : purchasedOrder.getCart()) {
            List<BatchStock> batchStocks = batchStockService.findAllByProductId(cart.getProducts().getId(), "F");
            Integer productQuantityTotal = batchStocks.stream().
                    reduce(0, (acc, e) -> acc + e.getCurrentQuantity(), Integer::sum);

            if (productQuantityTotal < cart.getQuantity()) {
                throw new RuntimeException("A quantidade do produto não é suficiente");
            }

        }
    }
}
