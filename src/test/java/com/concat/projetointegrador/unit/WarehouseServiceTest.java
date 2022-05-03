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
class WarehouseServiceTest {
    private WarehouseService service;
    private final WarehouseRepository warehouseRepositoryMock = Mockito.mock(WarehouseRepository.class);

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
}

