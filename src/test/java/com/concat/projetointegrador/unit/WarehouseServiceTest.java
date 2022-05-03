package com.concat.projetointegrador.unit;

import com.concat.projetointegrador.dto.WarehouseDTO;
import com.concat.projetointegrador.exception.EntityNotFound;
import com.concat.projetointegrador.model.Warehouse;
import com.concat.projetointegrador.repository.InboundOrderRepository;
import com.concat.projetointegrador.repository.WarehouseRepository;
import com.concat.projetointegrador.service.WarehouseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class WarehouseServiceTest {
    private WarehouseService service;
    private final WarehouseRepository warehouseRepositoryMock = Mockito.mock(WarehouseRepository.class);

    @Mock
    private InboundOrderService inboundOrderService;

    @BeforeEach
    public void init() {
        service = new WarehouseService(warehouseRepositoryMock);
    }

    private static Warehouse mockWarehouse(){
        return Warehouse.builder()
                .id(1L)
                .region("Zona Sul SP")
                .name("armazem dos congelados")
                .build();
    }

    @Test
    void shouldReturnWarehouseById() {
        WarehouseDTO warehouseDTO;
        Mockito.when(warehouseRepositoryMock.findById(Mockito.anyLong())).thenReturn(Optional.of(mockWarehouse()));
        warehouseDTO = service.findById(Mockito.anyLong());

        assertEquals("armazem dos congelados",warehouseDTO.getName());
        assertEquals("Zona Sul SP",warehouseDTO.getRegion());
    }

    @Test
    void shouldReturnWarehouseByIdNotFound() {
        Mockito.when(warehouseRepositoryMock.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        Throwable exception = assertThrows(EntityNotFound.class, () -> service.findById(Mockito.anyLong()));
        assertEquals("Armazém não encontrado!", exception.getMessage());
    }

    @Test
    void shouldCreateWarehouse() {
        WarehouseDTO warehouseDTO;
        Mockito.when(warehouseRepositoryMock.findByName(Mockito.any())).thenReturn(Optional.empty());
        Mockito.when(warehouseRepositoryMock.save(Mockito.any())).thenReturn(mockWarehouse());
        warehouseDTO = service.create(mockWarehouse());

        assertEquals("armazem dos congelados",warehouseDTO.getName());
        assertEquals("Zona Sul SP",warehouseDTO.getRegion());
    }

    @Test
    void shouldReturnErrorExistCreateProduct() {
        Mockito.when(warehouseRepositoryMock.findByName(Mockito.any())).thenReturn(Optional.of(mockWarehouse()));
        // Mockito.when(warehouseRepositoryMock.save(Mockito.any())).thenReturn(mockWarehouse());
        Throwable exception = assertThrows(RuntimeException.class, () -> service.create(mockWarehouse()));
        assertEquals("Esse armazém já existe!", exception.getMessage());
    }

    @Test
    public void shouldReturnQuantityProductByWarehouse() {
        inboundOrderService = Mockito.mock(InboundOrderService.class);
        List<BatchStock> batchStocks = Arrays.asList(
                batchStockMock(),
                batchStockMock()
        );
        Mockito.when(inboundOrderService.findById(Mockito.anyLong())).thenReturn(inboundOrderMock());
        List<WarehouseQuantityProductDTO> allProductForWarehouse = service.findAllProductForWarehouse(batchStocks, inboundOrderService);
        assertEquals(4, allProductForWarehouse.get(0).getTotalQuantity());
        assertEquals(warehouseMock().getId(), allProductForWarehouse.get(0).getWarehouseCode());
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
