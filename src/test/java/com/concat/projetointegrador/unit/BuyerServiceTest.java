package com.concat.projetointegrador.unit;

import com.concat.projetointegrador.exception.EntityNotFound;
import com.concat.projetointegrador.model.Buyer;
import com.concat.projetointegrador.repository.BuyerRepository;
import com.concat.projetointegrador.service.BuyerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BuyerServiceTest {
    private final BuyerRepository buyerRepository = mock(BuyerRepository.class);
    private final BuyerService buyerService = new BuyerService(buyerRepository);


    @Test
    void shouldReturnABuyerById() {
        when(buyerRepository.findById(anyLong())).thenReturn(Optional.of(mockBuyer()));
        Buyer result = buyerService.findById(1L);
        Buyer actual = mockBuyer();

        assertEquals(actual.getClass(), result.getClass());
    }

    @Test
    void shouldReturnExceptionWhenBuyerDoesntExist() {
        EntityNotFound exception = assertThrows(EntityNotFound.class, () -> buyerService.findById(1L));
        String expectedMessage = "Comprador não encontrado";
        String actualMessage = exception.getMessage();

        assertEquals(actualMessage, expectedMessage);
    }

    private Buyer mockBuyer() {
        return new Buyer(1L, "AnyName","AnyLastName", 11111111L);
    }


}