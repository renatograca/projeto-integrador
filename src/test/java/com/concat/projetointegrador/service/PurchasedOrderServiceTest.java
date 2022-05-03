package com.concat.projetointegrador.service;

import com.concat.projetointegrador.dto.PurchasedOrderDTO;
import com.concat.projetointegrador.model.*;
import com.concat.projetointegrador.repository.BatchStockRepository;
import com.concat.projetointegrador.repository.BuyerRepository;
import com.concat.projetointegrador.repository.CartRepository;
import com.concat.projetointegrador.repository.PurchasedOrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

class PurchasedOrderServiceTest {

    private PurchasedOrderRepository purchasedOrderRepository = Mockito.mock(PurchasedOrderRepository.class);
    private PurchasedOrderService purchasedOrderService;
    private PurchasedOrderDTO purchasedOrderDTO;
    private PurchasedOrder purchasedOrder;
    private BatchStockRepository batchStockRepository = Mockito.mock(BatchStockRepository.class);
    private BatchStockService batchStockService;
    private CartRepository cartRepository = Mockito.mock(CartRepository.class);
    private BuyerRepository buyerRepository = Mockito.mock(BuyerRepository.class);
    private BuyerService buyerService;
    private Buyer buyer;
    private Product product;
    private Seller seller;
    private List<Cart> carts;
    private Cart cart;

    @BeforeEach
    private void setUp() {
        buyerService = new BuyerService(buyerRepository);
        batchStockService = new BatchStockService(batchStockRepository);
        purchasedOrderService = new PurchasedOrderService(purchasedOrderRepository, batchStockService, cartRepository, buyerService);
        startSeller();
        startProduct();
        startBuyer();
        startListOfCart();
        startPurchasedOrder();
        startPurchasedOrderDTO();
    }

    @Test
    public void sholdReturnTotalPriceOfProdutsInCarts() {


    }

    @Test
    void findById() {
    }

    @Test
    void update() {
    }

    private void startSeller() {
        seller = Seller.builder().id(0L).name("V").lastName("?").build();
    }

    private void startProduct() {
        product = Product.builder()
                .id(1L)
                .name("Monster Energy 473ml")
                .volume(3)
                .price(BigDecimal.valueOf(8.99))
                .category(Category.CONGELADOS)
                .seller(seller)
                .build();
    }

    private void startBuyer() {
        buyer = Buyer.builder()
                .id(1L)
                .name("nome")
                .lastName("ultimo")
                .cpf(12345678910L)
                .build();
    }

    private void startListOfCart() {
        ArrayList<Cart> carts0 = new ArrayList();
        PurchasedOrder purchasedOrderForCarts = PurchasedOrder.builder()
                .id(1l)
                .status("aberto")
                .buyer(buyer)
                .cart(carts0)
                .build();
        cart = Cart.builder()
                .id(1L)
                .quantity(1)
                .products(product)
                .purchasedOrder(purchasedOrderForCarts)
                .build();
        carts0.add(cart);
        carts.add(cart);//testar adicionar novamente cart
    }

    private void startPurchasedOrder() {
        purchasedOrder = PurchasedOrder.builder()
                .id(2L)
                .status("aberto")
                .buyer(buyer)
                .cart(carts)
                .build();
    }

    private void startPurchasedOrderDTO() {
        purchasedOrderDTO = PurchasedOrderDTO.builder().price(BigDecimal.valueOf(8.99)).build();
    }

}