package com.concat.projetointegrador.unit;

import com.concat.projetointegrador.dto.ProductDTO;
import com.concat.projetointegrador.model.Category;
import com.concat.projetointegrador.model.Product;
import com.concat.projetointegrador.model.Seller;
import com.concat.projetointegrador.repository.ProductRepository;
import com.concat.projetointegrador.service.ProductService;
import com.concat.projetointegrador.service.SellerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    private ProductService service;
    private final ProductRepository productRepositoryMock = Mockito.mock(ProductRepository.class);
    private final SellerService sellerServiceMock = Mockito.mock(SellerService.class);

    @BeforeEach
    public void init() {
        service = new ProductService(productRepositoryMock, sellerServiceMock);
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

    @Test
    void shouldGetProductDTOById() throws Exception {
        ProductDTO productDTO;
        Mockito.when(productRepositoryMock.findById(Mockito.anyLong())).thenReturn(Optional.of(mockProduct()));
        productDTO = ProductDTO.convertToProductDTO(service.findById(Mockito.anyLong()));

        assertEquals("frango",productDTO.getName());
    }



}
