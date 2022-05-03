package com.concat.projetointegrador.unit;

import com.concat.projetointegrador.exception.EntityNotFound;
import com.concat.projetointegrador.model.Category;
import com.concat.projetointegrador.model.Sector;
import com.concat.projetointegrador.model.Supervisor;
import com.concat.projetointegrador.model.Warehouse;
import com.concat.projetointegrador.repository.SectorRepository;

import com.concat.projetointegrador.service.SectorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SectorServiceTest {
    private final SectorRepository sectorRepositoryMock = mock(SectorRepository.class);
    private final Warehouse warehouseMock = mock(Warehouse.class);
    private final Supervisor supervisorMock = mock(Supervisor.class);
    private final SectorService service = new SectorService(sectorRepositoryMock);

    @Test
    void shouldReturnASectorById() {
        Sector sector = mockSector();
        when(sectorRepositoryMock.findById(anyLong())).thenReturn(Optional.of(sector));
        Sector result = service.findById(1L);

        assertEquals(result.getClass(), sector.getClass());
    }

    @Test
    void shouldReturnExceptionWhenSectorDoesntExists() {
        EntityNotFound exception = Assertions.assertThrows(EntityNotFound.class, () -> service.findById(2L));

        String expectedMessage = "Setor não encontrado! Tente outro ID ou crie um novo Setor!";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldReturnASectorByCategory() {
        List<Sector> sectorList = Arrays.asList(
                mockSector(),
                mockSector(),
                mockSector()
        );
        List<Sector> resultList = service.findByCategory(Category.CONGELADOS);
        when(sectorRepositoryMock.findByCategory(Category.CONGELADOS)).thenReturn(sectorList);

        resultList.forEach(s1 -> sectorList.forEach(s2 -> assertEquals(s1.getClass(), s2.getClass())));
    }

    private Sector mockSector() {
        return new Sector(1L, warehouseMock, 100, supervisorMock, Category.CONGELADOS);
    }


}