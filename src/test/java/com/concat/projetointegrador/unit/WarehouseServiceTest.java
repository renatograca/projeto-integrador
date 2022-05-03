package com.concat.projetointegrador.unit;

import com.concat.projetointegrador.dto.WarehouseQuantityProductDTO;
import com.concat.projetointegrador.model.*;
import com.concat.projetointegrador.repository.WarehouseRepository;
import com.concat.projetointegrador.service.InboundOrderService;
import com.concat.projetointegrador.service.WarehouseService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WarehouseServiceTest {

    @Mock
    private WarehouseRepository warehouseRepository;

    @Mock
    private InboundOrderService inboundOrderService;
    private WarehouseService warehouseService;

    @Test
    public void shouldReturnQuantityProductByWarehouse() {
        warehouseRepository = Mockito.mock(WarehouseRepository.class);
        warehouseService = new WarehouseService(warehouseRepository);
        inboundOrderService = Mockito.mock(InboundOrderService.class);
        List<BatchStock> batchStocks = Arrays.asList(
                batchStockMock(),
                batchStockMock()
        );
        Mockito.when(inboundOrderService.findById(Mockito.anyLong())).thenReturn(inboundOrderMock());
        List<WarehouseQuantityProductDTO> allProductForWarehouse = warehouseService.findAllProductForWarehouse(batchStocks, inboundOrderService);
        assertEquals(4, allProductForWarehouse.get(0).getTotalQuantity());
    }

    private BatchStock batchStockMock() {
        return BatchStock.builder()
                .id(1L)
                .category(Category.CONGELADOS)
                .currentQuantity(2)
                .dueDate(LocalDate.now())
                .manufacturingDate(LocalDate.now())
                .currentTemperature(2)
                .initialQuantity(10)
                .initialTemperature(1)
                .manufacturingTime(LocalTime.now())
                .product(productMock())
                .inboundOrder(InboundOrder.builder().id(1L).build())
                .build();
    }

    private Product productMock() {
        return Product.builder()
                .id(1L)
                .category(Category.CONGELADOS)
                .name("Melhor Produto ou anuncio")
                .price(BigDecimal.valueOf(50))
                .seller(sellerMock())
                .volume(1)
                .build();
    }

    private Seller sellerMock() {
        return Seller.builder()
                .id(1L)
                .name("Melhor")
                .lastName("Seller")
                .build();
    }

    private InboundOrder inboundOrderMock() {
        return InboundOrder.builder()
                .id(1L)
                .batchStock(Arrays.asList(batchStockMock(), batchStockMock()))
                .sector(sectorMock())
                .build();
    }
    private  Sector sectorMock() {
        return Sector.builder()
                .id(1L)
                .warehouse(warehouseMock())
                .category(Category.CONGELADOS)
                .capacity(20)
                .supervisor(supervisorMock())
                .build();
    }
    private Warehouse warehouseMock(){
        return Warehouse.builder()
                .id(1L)
                .name("Melhor warehouse")
                .region("Melhor regiao")
                .build();
    }
    private Supervisor supervisorMock(){
        return Supervisor.builder()
                .id(1L)
                .name("Melhor")
                .lastName("Supervisor")
                .build();
    }
}
