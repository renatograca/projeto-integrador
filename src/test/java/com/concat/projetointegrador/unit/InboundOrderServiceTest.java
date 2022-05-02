package com.concat.projetointegrador.unit;

import com.concat.projetointegrador.exception.EntityNotFound;
import com.concat.projetointegrador.model.*;
import com.concat.projetointegrador.repository.BatchStockRepository;
import com.concat.projetointegrador.repository.InboundOrderRepository;
import com.concat.projetointegrador.service.InboundOrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

class InboundOrderServiceTest {

    @Mock
    private static InboundOrderRepository inboundOrderRepository = Mockito.mock(InboundOrderRepository.class);

    @Mock
    private static BatchStockRepository batchStockRepository = Mockito.mock(BatchStockRepository.class);

    private static InboundOrderService inboundOrderService;

    @BeforeAll
    public static void init() {
        inboundOrderService = new InboundOrderService(inboundOrderRepository, batchStockRepository,  null);
    }

    @Test
    public void shouldReturnAnInboundOrderById() {
        Mockito
            .when(inboundOrderRepository.findById(1L))
            .thenReturn(inboundOrderMock());

        InboundOrder inboundOrder = inboundOrderService.findById(1L);

        assertEquals(1L, inboundOrder.getId());
    }

    @Test
    public void shouldCreateAndReturnAnInboundOrder() {
        Mockito
            .when(batchStockRepository.saveAll(batchStockMock()))
            .thenReturn(batchStockMock());
        Mockito
                .when(inboundOrderRepository.save(Mockito.any()))
                .thenReturn(inboundOrderMock().get());


        InboundOrder inboundOrder = inboundOrderService.create(inboundOrderMock().get());

        assertEquals(1L, inboundOrder.getId());
    }

    @Test
    public void shouldUpdateAndReturnAnInboundOrder() {
        Mockito
                .when(inboundOrderRepository.findById(1L))
                .thenReturn(inboundOrderMock());
        Mockito
                .when(inboundOrderRepository.save(Mockito.any()))
                .thenReturn(inboundOrderMock().get());

        InboundOrder inboundOrder = inboundOrderService.update(1L,inboundOrderMock().get());

        assertEquals(1L, inboundOrder.getId());
    }

    @Test
    public void shouldThrowAEntityNotFoundException() {
        Mockito
                .when(inboundOrderRepository.findById(99L))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(EntityNotFound.class, () -> {
            inboundOrderService.findById(99L);
        });

    }

    @Test
    public void shouldReturnAListOfInboundOrder() {
        Mockito
                .when(inboundOrderRepository.findAll())
                .thenReturn(Arrays.asList(inboundOrderMock().get(), inboundOrderMock().get()));

        assertEquals(2, inboundOrderService.findBySectorId(1L).size());

    }

    @Test
    public void shouldReturnAllInboundOrder() {
        Mockito
                .when(inboundOrderRepository.findAll())
                .thenReturn(Arrays.asList(inboundOrderMock().get(), inboundOrderMock().get()));

        assertEquals(2, inboundOrderService.findAll().size());

    }

    private static Optional<InboundOrder> inboundOrderMock(){
        return Optional.of(
                InboundOrder
                .builder()
                .id(1L)
                .sector(sectorMock())
                .batchStock(batchStockMock())
                .build());
    }

    private static Sector sectorMock() {
        return Sector
                .builder()
                .id(1L)
                .capacity(1)
                .warehouse(warehouseMock())
                .supervisor(supervisorMock())
                .category(Category.CONGELADOS)
                .build();
    }

    private static Warehouse warehouseMock() {
        return Warehouse
                .builder()
                .id(1L)
                .name("")
                .region("")
                .build();
    }

    private static Supervisor supervisorMock() {
        return Supervisor.builder()
                .id(1L)
                .name("")
                .lastName("")
                .build();
    }

    private static List<BatchStock> batchStockMock(){

        ArrayList<BatchStock> batchStocks = new ArrayList<>();
        InboundOrder inboundOrder = InboundOrder
                .builder()
                .id(1L)
                .sector(sectorMock())
                .batchStock(batchStocks)
                .build();

        BatchStock batchStock =
                BatchStock
                    .builder()
                    .id(1L)
                    .currentQuantity(1)
                    .initialQuantity(1)
                    .initialTemperature(1)
                    .currentTemperature(1)
                    .product(productMock())
                    .dueDate(LocalDate.now())
                    .category(Category.CONGELADOS)
                    .inboundOrder(inboundOrder)
                    .manufacturingDate(LocalDate.now())
                    .manufacturingTime(LocalTime.now())
                    .build();

        batchStocks.add(batchStock);


        return batchStocks;
    }

    private static Product productMock() {
        return Product
                .builder()
                .id(1L)
                .name("")
                .volume(1)
                .seller(sellerMock())
                .price(BigDecimal.TEN)
                .category(Category.CONGELADOS)
                .build();
    }

    private static Seller sellerMock() {
        return Seller
                .builder()
                .id(1L)
                .name("")
                .lastName("")
                .build();
    }
}
