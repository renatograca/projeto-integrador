package com.concat.projetointegrador.model;

import com.concat.projetointegrador.repository.SellerRepository;
import com.concat.projetointegrador.service.SellerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

class SellerServiceTest {

    public static final long ID = 1L;
    public static final String NAME = "V";
    public static final String LAST_NAME = "?";
    private SellerService service;
    private SellerRepository repository = Mockito.mock(SellerRepository.class);
    private Optional<Seller> optionalSeller;
    private Seller seller;

    private void setUp() {
        service = new SellerService(repository);
        startSeller();
    }

    @Test
    void whenFindByIDThenReturnASeller() {
        setUp();
        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(optionalSeller);
        Seller response = service.findByID(ID);
        Assertions.assertEquals(response.getClass(), seller.getClass());
    }

    private void startSeller() {
        seller = Seller.builder().id(ID).name(NAME).lastName(LAST_NAME).build();
        optionalSeller = Optional.of(Seller.builder().id(ID).name(NAME).lastName(LAST_NAME).build());
    }

}
