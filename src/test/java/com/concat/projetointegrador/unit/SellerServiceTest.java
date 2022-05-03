package com.concat.projetointegrador.unit;

import com.concat.projetointegrador.exception.EntityNotFound;
import com.concat.projetointegrador.model.Seller;
import com.concat.projetointegrador.repository.SellerRepository;
import com.concat.projetointegrador.service.SellerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

public class SellerServiceTest {

    public static final long ID = 1L;
    public static final String NAME = "V";
    public static final String LAST_NAME = "?";
    private SellerService service;
    private SellerRepository repository = Mockito.mock(SellerRepository.class);
    private Optional<Seller> optionalSeller;
    private Seller seller;

    @BeforeEach
    private void setUp() {
        service = new SellerService(repository);
        startSeller();
    }

    @Test
    public void whenFindByIDThenReturnASeller() {
        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(optionalSeller);
        Seller response = service.findByID(ID);
        Assertions.assertEquals(response.getClass(), seller.getClass());
    }

    @Test
    public void shouldReturnAnErrorWhenNotFoundASeller() {
        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        EntityNotFound entitityNotFound = Assertions
                .assertThrows(EntityNotFound.class, () -> service.findByID(Mockito.anyLong()));

        Assertions.assertEquals(entitityNotFound.getMessage(), "Vendedor não existe.");
    }

    private void startSeller() {
        seller = Seller.builder().id(ID).name(NAME).lastName(LAST_NAME).build();
        optionalSeller = Optional.of(Seller.builder().id(ID).name(NAME).lastName(LAST_NAME).build());
    }
}
