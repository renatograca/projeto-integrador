package com.concat.projetointegrador.unit;

import com.concat.projetointegrador.dto.BatchStockFilterDTO;
import com.concat.projetointegrador.dto.ProductDTO;
import com.concat.projetointegrador.exception.EntityNotFound;
import com.concat.projetointegrador.model.*;
import com.concat.projetointegrador.repository.BatchStockRepository;
import com.concat.projetointegrador.repository.ProductRepository;
import com.concat.projetointegrador.service.BatchStockService;
import com.concat.projetointegrador.service.ProductService;
import net.bytebuddy.implementation.bind.annotation.Super;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class BatchStockServiceTest {

    private BatchStockService service;
    private final BatchStockRepository batchStockRepositoryMock = Mockito.mock(BatchStockRepository.class);

    @BeforeEach
    public void init() {
        service = new BatchStockService(batchStockRepositoryMock);
    }

    private Product mockProduct(){
        return Product.builder()
                .id(1L)
                .price(BigDecimal.valueOf(10))
                .category(Category.CONGELADOS)
                .name("frango")
                .volume(1)
                .seller(Seller.builder().id(1L).name("Jonas").lastName("Viana").build())
                .build();
    }

    private BatchStock mockBatchStock() {
        return BatchStock.builder()
                .category(Category.CONGELADOS)
                .currentQuantity(10)
                .initialQuantity(10)
                .currentTemperature(9)
                .initialTemperature(8)
                .manufacturingDate(LocalDate.now())
                .manufacturingTime(LocalTime.now())
                .dueDate(LocalDate.of(2023, 10, 10))
                .product(mockProduct())
                .build();
    }

    private List<BatchStock> mockListBatchStock() {
        List<BatchStock> listBatchStock = Arrays.asList(
                BatchStock.builder()
                        .id(0L)
                        .category(Category.CONGELADOS)
                        .currentQuantity(10)
                        .initialQuantity(10)
                        .currentTemperature(9)
                        .initialTemperature(8)
                        .manufacturingDate(LocalDate.now())
                        .manufacturingTime(LocalTime.now())
                        .dueDate(LocalDate.of(2023, 10, 10))
                        .product(mockProduct())
                        .build(),
                BatchStock.builder()
                        .id(1L)
                        .category(Category.CONGELADOS)
                        .currentQuantity(5)
                        .initialQuantity(5)
                        .currentTemperature(9)
                        .initialTemperature(8)
                        .manufacturingDate(LocalDate.now())
                        .manufacturingTime(LocalTime.now())
                        .dueDate(LocalDate.of(2023, 11, 10))
                        .product(mockProduct())
                        .build());
        return listBatchStock;
    }

    private List<InboundOrder> mockListInboundOrder(){
        Supervisor supervisor = Supervisor.builder().name("Maria").lastName("Ferraz").build();
        Warehouse werehouse = Warehouse.builder().name("Casa Verder").region("São Paulo").build();
        Sector sector = Sector.builder().id(0L).warehouse(werehouse).supervisor(supervisor).category(Category.CONGELADOS).capacity(100).build();
        List<InboundOrder> listInboundOrder = Arrays.asList(
                InboundOrder.builder()
                .id(0L)
                .sector(sector)
                .batchStock(mockListBatchStock())
                .build()
        );
        return listInboundOrder;
    }

    @Test
    void shouldReturnGetBatchStockById() throws Exception {
        BatchStock batchStock;
        Mockito.when(batchStockRepositoryMock.findById(Mockito.anyLong())).thenReturn(Optional.of(mockBatchStock()));
        batchStock = service.findById(Mockito.anyLong());
        assertEquals("frango", batchStock.getProduct().getName());
    }

    @Test
    void shouldReturnGetBatchStockByIdNotFound() throws Exception {
        Mockito.when(batchStockRepositoryMock.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        Throwable exception = assertThrows(EntityNotFound.class, () -> service.findById(Mockito.anyLong()));
        assertEquals("O estoque não foi encontrado!!", exception.getMessage());
    }

    @Test
    void shouldCreateBatchStock() throws Exception {
        BatchStock batchStock;
        Mockito.when(batchStockRepositoryMock.save(Mockito.any())).thenReturn(mockBatchStock());
        batchStock = service.create(mockBatchStock());
        assertEquals("frango", batchStock.getProduct().getName());
    }

    @Test
    void shouldReturnProductByBatchStock() throws Exception {
        List<BatchStock> batchStocks;
        Mockito.when(batchStockRepositoryMock.findAllByProductId(Mockito.anyLong())).thenReturn(mockListBatchStock());
        batchStocks = service.findByProductId(Mockito.anyLong(), 1);
        assertEquals("frango", batchStocks.get(0).getProduct().getName());
    }

    @Test
    void shouldReturnProductByBatchStockNotFound() throws Exception {
        Mockito.when(batchStockRepositoryMock.findAllByProductId(Mockito.anyLong())).thenReturn(new ArrayList<>());
        Throwable exception = assertThrows(EntityNotFound.class, () -> service.findByProductId(Mockito.anyLong(), 1));
        assertEquals("Este produto não existe", exception.getMessage());
    }

    @Test
    void shouldReturnProductByBatchStockInsufficientAmount() throws Exception {
        Mockito.when(batchStockRepositoryMock.findAllByProductId(Mockito.anyLong())).thenReturn(mockListBatchStock());
        Throwable exception = assertThrows(EntityNotFound.class, () -> service.findByProductId(Mockito.anyLong(), 20));
        assertEquals("Estoque insuficiente", exception.getMessage());
    }



}
