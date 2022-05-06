package com.concat.projetointegrador.unit;

import com.concat.projetointegrador.dto.PurchasedOrderDTO;
import com.concat.projetointegrador.exception.EntityNotFound;
import com.concat.projetointegrador.model.*;
import com.concat.projetointegrador.repository.CartRepository;
import com.concat.projetointegrador.repository.PurchasedOrderRepository;
import com.concat.projetointegrador.service.BatchStockService;
import com.concat.projetointegrador.service.BuyerService;
import com.concat.projetointegrador.service.PurchasedOrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

class PurchasedOrderServiceTest {

    private PurchasedOrderRepository purchasedOrderRepository = Mockito.mock(PurchasedOrderRepository.class);
    private Optional<PurchasedOrder> optionalPurchasedOrder;
    private PurchasedOrderService purchasedOrderService;
    private PurchasedOrderDTO purchasedOrderDTO;
    private PurchasedOrder purchasedOrder;
    private BatchStockService batchStockService = Mockito.mock(BatchStockService.class);
    private List<BatchStock> batchStocks;
    private BatchStock batchStock;
    private CartRepository cartRepository = Mockito.mock(CartRepository.class);
    private BuyerService buyerService = Mockito.mock(BuyerService.class);
    private Sector sector;
    private Supervisor supervisor;
    private Warehouse warehouse;
    private Buyer buyer;
    private Product product;
    private Seller seller;
    private List<Cart> carts;
    private Cart cart;
    private BatchStock batchStockWithProductThirtyDaysToExpire;
    private BatchStock batchStockWithProductTwentyDaysToExpire;
    private BatchStock batchStockWithProductTenDaysToExpire;
    private List<BatchStock> batchStocksWithProductThirtyDaysToExpire;
    private List<BatchStock> batchStocksWithProductTwentyDaysToExpire;
    private List<BatchStock> batchStocksWithProductTenDaysToExpire;



    @BeforeEach
    private void setUp() {
        purchasedOrderService = new PurchasedOrderService(purchasedOrderRepository, batchStockService, cartRepository, buyerService);
        startSeller();
        startProduct();
        startBuyer();
        startListOfCart();
        startPurchasedOrder();
        startPurchasedOrderDTO();
        startSupervisor();
        startWarehouse();
        startSector();
        startBatchStocks();
    }

    @Test
    public void shouldReturnProductWithThirtyDiscount() {
        Mockito.when(buyerService.findById(Mockito.anyLong())).thenReturn(buyer);
        Mockito.when(batchStockService.findByProductId(Mockito.anyLong(), Mockito.anyInt())).thenReturn(batchStocksWithProductTenDaysToExpire);
        Mockito.when(purchasedOrderRepository.save(Mockito.any())).thenReturn(purchasedOrder);
        Mockito.when(cartRepository.saveAll(Mockito.any())).thenReturn(carts);

        BigDecimal thirtyPercent = BigDecimal.valueOf(0.3);
        PurchasedOrderDTO response = purchasedOrderService.create(purchasedOrder);
        BigDecimal price = purchasedOrderDTO.getPrice();
        BigDecimal priceWithDiscount = price.subtract(price.multiply(thirtyPercent));

        Assertions.assertEquals(response.getPrice(), priceWithDiscount);
    }
    @Test
    public void shouldReturnProductWithTwentyDiscount() {
        Mockito.when(buyerService.findById(Mockito.anyLong())).thenReturn(buyer);
        Mockito.when(batchStockService.findByProductId(Mockito.anyLong(), Mockito.anyInt())).thenReturn(batchStocksWithProductTwentyDaysToExpire);
        Mockito.when(purchasedOrderRepository.save(Mockito.any())).thenReturn(purchasedOrder);
        Mockito.when(cartRepository.saveAll(Mockito.any())).thenReturn(carts);

        BigDecimal twentyPercent = BigDecimal.valueOf(0.2);
        PurchasedOrderDTO response = purchasedOrderService.create(purchasedOrder);
        BigDecimal price = purchasedOrderDTO.getPrice();
        BigDecimal priceWithDiscount = price.subtract(price.multiply(twentyPercent));

        Assertions.assertEquals(response.getPrice(), priceWithDiscount);
    }
    @Test
    public void shouldReturnProductWithTenDiscount() {
        Mockito.when(buyerService.findById(Mockito.anyLong())).thenReturn(buyer);
        Mockito.when(batchStockService.findByProductId(Mockito.anyLong(), Mockito.anyInt())).thenReturn(batchStocksWithProductThirtyDaysToExpire);
        Mockito.when(purchasedOrderRepository.save(Mockito.any())).thenReturn(purchasedOrder);
        Mockito.when(cartRepository.saveAll(Mockito.any())).thenReturn(carts);

        PurchasedOrderDTO response = purchasedOrderService.create(purchasedOrder);

        BigDecimal tenPercent = BigDecimal.valueOf(0.1);
        BigDecimal price = purchasedOrderDTO.getPrice();
        BigDecimal priceWithDiscount = price.subtract(price.multiply(tenPercent));

        Assertions.assertEquals(priceWithDiscount, response.getPrice());
    }

    @Test
    public void shouldReturnTotalPriceOfProductsInCartsWhenCreatePurchasedOrder() {

        Mockito.when(buyerService.findById(Mockito.anyLong())).thenReturn(buyer);
        Mockito.when(batchStockService.findByProductId(Mockito.anyLong(), Mockito.anyInt())).thenReturn(batchStocks);
        Mockito.when(purchasedOrderRepository.save(Mockito.any())).thenReturn(purchasedOrder);
        Mockito.when(cartRepository.saveAll(Mockito.any())).thenReturn(carts);

        PurchasedOrderDTO response = purchasedOrderService.create(purchasedOrder);

        Assertions.assertEquals(response.getPrice(), purchasedOrderDTO.getPrice());

    }

    @Test
    public void whenFindByIDThenReturnAPurchasedOrder() {
        Mockito.when(purchasedOrderRepository.findById(Mockito.anyLong())).thenReturn(optionalPurchasedOrder);
        PurchasedOrder response = purchasedOrderService.findById(1L);
        Assertions.assertEquals(response.getClass(), purchasedOrder.getClass());
    }

    @Test
    public void shouldReturnAnErrorWhenNotFoundAPurchasedOrder() {
        Mockito.when(purchasedOrderRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        EntityNotFound entityNotFound = Assertions
                .assertThrows(EntityNotFound.class, () -> purchasedOrderService.findById(Mockito.anyLong()));

        Assertions.assertEquals(entityNotFound.getMessage(), "Pedido não existe");
    }

    @Test
    void shouldUpdateStatusInPurchasedOrderAndUpdateBatchStock() {


        Mockito.when(purchasedOrderRepository.findById(Mockito.anyLong())).thenReturn(optionalPurchasedOrder);
        startUpdateInPurchasedOrder();
        Mockito.when(purchasedOrderRepository.save(Mockito.any())).thenReturn(purchasedOrder);
        Mockito.when(batchStockService.findAllByProductId(Mockito.anyLong(), Mockito.anyString())).thenReturn(batchStocks);
        Mockito.when(batchStockService.create(Mockito.any())).thenReturn(batchStock);

        PurchasedOrder response = purchasedOrderService.update(2L);

        Assertions.assertEquals(response.getStatus(), "finalizado");

    }

    @Test
    void returnRuntimeExceptionWhenProductQuantityIsNotEnough() {


        Mockito.when(purchasedOrderRepository.findById(Mockito.anyLong())).thenReturn(optionalPurchasedOrder);
        batchStock.setCurrentQuantity(0);
        Mockito.when(batchStockService.findAllByProductId(Mockito.anyLong(), Mockito.anyString())).thenReturn(batchStocks);
        RuntimeException response = Assertions.assertThrows(RuntimeException.class, () -> purchasedOrderService.update(Mockito.anyLong()));

        Assertions.assertEquals(response.getMessage(), "A quantidade do produto não é suficiente");

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
        carts = new ArrayList();
        PurchasedOrder purchasedOrderForCarts = PurchasedOrder.builder()
                .id(1l)
                .status("aberto")
                .buyer(buyer)
                .cart(carts0)
                .build();
        cart = Cart.builder()
                .id(1L)
                .quantity(1)
                .product(product)
                .purchasedOrder(purchasedOrderForCarts)
                .build();
        carts0.add(cart);
        carts.add(cart);
        carts.add(cart);
    }

    private void startPurchasedOrder() {
        purchasedOrder = PurchasedOrder.builder()
                .id(2L)
                .status("aberto")
                .buyer(buyer)
                .cart(carts)
                .build();
        optionalPurchasedOrder = Optional.of(PurchasedOrder.builder()
                .id(2L)
                .status("aberto")
                .buyer(buyer)
                .cart(carts)
                .build());
    }

    private void startPurchasedOrderDTO() {
        purchasedOrderDTO = PurchasedOrderDTO.builder().price(BigDecimal.valueOf(17.98)).build();
    }

    private void startSupervisor() {
        supervisor = Supervisor.builder()
                .id(1L)
                .name("")
                .lastName("")
                .build();
    }

    private void startWarehouse() {
        warehouse = Warehouse
                .builder()
                .id(1L)
                .name("")
                .region("")
                .build();
    }

    private void startSector() {
        sector = Sector
                .builder()
                .id(1L)
                .capacity(1)
                .warehouse(warehouse)
                .supervisor(supervisor)
                .category(Category.CONGELADOS)
                .build();
    }

    private void startBatchStock() {
        InboundOrder inboundOrder = InboundOrder
                .builder()
                .id(1L)
                .sector(sector)
                .batchStock(batchStocks)
                .build();

        batchStock =
                BatchStock
                        .builder()
                        .id(1L)
                        .currentQuantity(60)
                        .initialQuantity(60)
                        .initialTemperature(1)
                        .currentTemperature(1)
                        .product(product)
                        .dueDate(LocalDate.MAX)
                        .category(Category.CONGELADOS)
                        .inboundOrder(inboundOrder)
                        .manufacturingDate(LocalDate.now())
                        .manufacturingTime(LocalTime.now())
                        .build();

        batchStockWithProductThirtyDaysToExpire = BatchStock
                .builder()
                .id(1L)
                .currentQuantity(60)
                .initialQuantity(60)
                .initialTemperature(1)
                .currentTemperature(1)
                .product(product)
                .dueDate(LocalDate.ofEpochDay(LocalDate.now().toEpochDay() + 29))
                .category(Category.CONGELADOS)
                .inboundOrder(inboundOrder)
                .manufacturingDate(LocalDate.now())
                .manufacturingTime(LocalTime.now())
                .build();

        batchStockWithProductTwentyDaysToExpire = BatchStock
                .builder()
                .id(1L)
                .currentQuantity(60)
                .initialQuantity(60)
                .initialTemperature(1)
                .currentTemperature(1)
                .product(product)
                .dueDate(LocalDate.ofEpochDay(LocalDate.now().toEpochDay() + 19))
                .category(Category.CONGELADOS)
                .inboundOrder(inboundOrder)
                .manufacturingDate(LocalDate.now())
                .manufacturingTime(LocalTime.now())
                .build();

        batchStockWithProductTenDaysToExpire = BatchStock
                .builder()
                .id(1L)
                .currentQuantity(60)
                .initialQuantity(60)
                .initialTemperature(1)
                .currentTemperature(1)
                .product(product)
                .dueDate(LocalDate.ofEpochDay(LocalDate.now().toEpochDay() + 9))
                .category(Category.CONGELADOS)
                .inboundOrder(inboundOrder)
                .manufacturingDate(LocalDate.now())
                .manufacturingTime(LocalTime.now())
                .build();
    }

    private void startBatchStocks() {
        batchStocksWithProductExpiring = new ArrayList<>();
        batchStocksWithProductThirtyDaysToExpire = new ArrayList<>();
        batchStocksWithProductTwentyDaysToExpire = new ArrayList<>();
        batchStocksWithProductTenDaysToExpire = new ArrayList<>();
        batchStocks = new ArrayList<>();
        startBatchStock();


        batchStocksWithProductThirtyDaysToExpire.add(batchStockWithProductThirtyDaysToExpire);
        batchStocksWithProductTwentyDaysToExpire.add(batchStockWithProductTwentyDaysToExpire);
        batchStocksWithProductTenDaysToExpire.add(batchStockWithProductTenDaysToExpire);
        batchStocks.add(batchStock);
    }

    private void startUpdateInPurchasedOrder() {

        purchasedOrder = PurchasedOrder.builder()
                .id(2L)
                .status("finalizado")
                .buyer(buyer)
                .cart(carts)
                .build();
        optionalPurchasedOrder = Optional.of(PurchasedOrder.builder()
                .id(2L)
                .status("finalizado")
                .buyer(buyer)
                .cart(carts)
                .build());

    }

}
