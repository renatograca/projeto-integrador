package com.concat.projetointegrador.unit;

import com.concat.projetointegrador.exception.EntityNotFound;
import com.concat.projetointegrador.model.Seller;
import com.concat.projetointegrador.repository.PurchasedOrderRepository;
import com.concat.projetointegrador.repository.SellerRepository;
import com.concat.projetointegrador.service.SellerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Optional;

public class SellerServiceTest {

    public static final long ID = 1L;
    public static final String NAME = "V";
    public static final String USERNAME = "OnlyV";
    public static final String PASSWORD = "NANI!!";
    public static final String LAST_NAME = "?";
    private SellerService service;
    private SellerRepository repository = Mockito.mock(SellerRepository.class);
    private PurchasedOrderRepository purchasedOrderRepository = Mockito.mock(PurchasedOrderRepository.class);
    private HashMap<Long, Integer> sellersSales;
    private Optional<Seller> optionalSeller;
    private Seller seller;
    private Seller objectSeller;

    @BeforeEach
    private void setUp() {
        service = new SellerService(repository, purchasedOrderRepository);
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
        EntityNotFound entityNotFound = Assertions
                .assertThrows(EntityNotFound.class, () -> service.findByID(Mockito.anyLong()));

        Assertions.assertEquals(entityNotFound.getMessage(), "Vendedor não existe.");
    }

    @Test
    public void whenCreateASellerThenReturnASeller() {
        Mockito.when(repository.save(Mockito.any())).thenReturn(seller);
        Seller response = service.create(objectSeller);
        Assertions.assertEquals(response.getClass(), seller.getClass());
    }

    @Test
    public void when

    private void startSeller() {
        seller = Seller.builder().id(ID).username(USERNAME).password(PASSWORD).name(NAME).lastName(LAST_NAME).build();
        objectSeller = Seller.builder().username(USERNAME).password(PASSWORD).name(NAME).lastName(LAST_NAME).build();
        optionalSeller = Optional.of(Seller.builder().id(ID).username(USERNAME).password(PASSWORD).name(NAME).lastName(LAST_NAME).build());
    }
}
/**
 * [
 *        {
 * 		"id": 1,
 * 		"date": "2022-05-04",
 * 		"status": "fechado",
 * 		"buyer": {
 * 			"id": 1,
 * 			"username": "buyer",
 * 			"password": "$2a$10$z6ME137SMQ5Fsd7f5t8hj.Vx6JGrWdmXg6kqNsuuI/FdC6EAj2U26",
 * 			"name": "Renatinho",
 * 			"lastName": "Gracinha",
 * 			"cpf": 12345478911,
 * 			"enabled": true,
 * 			"authorities": [
 *                {
 * 					"authority": "Buyer"
 *                }
 * 			],
 * 			"discriminatorValue": "Buyer",
 * 			"accountNonExpired": true,
 * 			"accountNonLocked": true,
 * 			"credentialsNonExpired": true
 *        },
 * 		"cart": [
 *            {
 * 				"id": 1,
 * 				"quantity": 1,
 * 				"product": {
 * 					"id": 1,
 * 					"name": "carne",
 * 					"volume": 1,
 * 					"price": 20.00,
 * 					"category": "CONGELADOS",
 * 					"seller": {
 * 						"id": 4,
 * 						"username": "seller",
 * 						"password": "$2a$10$QgOSNetrzdULkQze3ZVijOnLRGrKsRbOaGCbyJNI9ZtAWQrLOXuve",
 * 						"name": "V",
 * 						"lastName": "Fraga",
 * 						"cpf": 12345678901,
 * 						"enabled": true,
 * 						"authorities": [
 *                            {
 * 								"authority": "Seller"
 *                            }
 * 						],
 * 						"discriminatorValue": "Seller",
 * 						"accountNonExpired": true,
 * 						"accountNonLocked": true,
 * 						"credentialsNonExpired": true
 *                    }
 *                }
 *            },
 *            {
 * 				"id": 3,
 * 				"quantity": 1,
 * 				"product": {
 * 					"id": 3,
 * 					"name": "frango",
 * 					"volume": 1,
 * 					"price": 10.00,
 * 					"category": "CONGELADOS",
 * 					"seller": {
 * 						"id": 4,
 * 						"username": "seller",
 * 						"password": "$2a$10$QgOSNetrzdULkQze3ZVijOnLRGrKsRbOaGCbyJNI9ZtAWQrLOXuve",
 * 						"name": "V",
 * 						"lastName": "Fraga",
 * 						"cpf": 12345678901,
 * 						"enabled": true,
 * 						"authorities": [
 *                            {
 * 								"authority": "Seller"
 *                            }
 * 						],
 * 						"discriminatorValue": "Seller",
 * 						"accountNonExpired": true,
 * 						"accountNonLocked": true,
 * 						"credentialsNonExpired": true
 *                    }
 *                }
 *            },
 *            {
 * 				"id": 2,
 * 				"quantity": 1,
 * 				"product": {
 * 					"id": 4,
 * 					"name": "batata",
 * 					"volume": 1,
 * 					"price": 20.00,
 * 					"category": "REFRIGERADOS",
 * 					"seller": {
 * 						"id": 7,
 * 						"username": "seller3",
 * 						"password": "$2a$10$QgOSNetrzdULkQze3ZVijOnLRGrKsRbOaGCbyJNI9ZtAWQrLOXuve",
 * 						"name": "Vini",
 * 						"lastName": "Fraga",
 * 						"cpf": 12345678901,
 * 						"enabled": true,
 * 						"authorities": [
 *                            {
 * 								"authority": "Seller"
 *                            }
 * 						],
 * 						"discriminatorValue": "Seller",
 * 						"accountNonExpired": true,
 * 						"accountNonLocked": true,
 * 						"credentialsNonExpired": true
 *                    }
 *                }
 *            }
 * 		]
 *    },
 *    {
 * 		"id": 3,
 * 		"date": "2022-05-04",
 * 		"status": "fechado",
 * 		"buyer": {
 * 			"id": 1,
 * 			"username": "buyer",
 * 			"password": "$2a$10$z6ME137SMQ5Fsd7f5t8hj.Vx6JGrWdmXg6kqNsuuI/FdC6EAj2U26",
 * 			"name": "Renatinho",
 * 			"lastName": "Gracinha",
 * 			"cpf": 12345478911,
 * 			"enabled": true,
 * 			"authorities": [
 *                {
 * 					"authority": "Buyer"
 *                }
 * 			],
 * 			"discriminatorValue": "Buyer",
 * 			"accountNonExpired": true,
 * 			"accountNonLocked": true,
 * 			"credentialsNonExpired": true
 *        },
 * 		"cart": [
 *            {
 * 				"id": 6,
 * 				"quantity": 1,
 * 				"product": {
 * 					"id": 2,
 * 					"name": "alface",
 * 					"volume": 1,
 * 					"price": 2.00,
 * 					"category": "FRESCOS",
 * 					"seller": {
 * 						"id": 5,
 * 						"username": "seller1",
 * 						"password": "$2a$10$QgOSNetrzdULkQze3ZVijOnLRGrKsRbOaGCbyJNI9ZtAWQrLOXuve",
 * 						"name": "Vi",
 * 						"lastName": "Fraga",
 * 						"cpf": 12345678901,
 * 						"enabled": true,
 * 						"authorities": [
 *                            {
 * 								"authority": "Seller"
 *                            }
 * 						],
 * 						"discriminatorValue": "Seller",
 * 						"accountNonExpired": true,
 * 						"accountNonLocked": true,
 * 						"credentialsNonExpired": true
 *                    }
 *                }
 *            }
 * 		]
 *    },
 *    {
 * 		"id": 2,
 * 		"date": "2022-05-04",
 * 		"status": "fechado",
 * 		"buyer": {
 * 			"id": 1,
 * 			"username": "buyer",
 * 			"password": "$2a$10$z6ME137SMQ5Fsd7f5t8hj.Vx6JGrWdmXg6kqNsuuI/FdC6EAj2U26",
 * 			"name": "Renatinho",
 * 			"lastName": "Gracinha",
 * 			"cpf": 12345478911,
 * 			"enabled": true,
 * 			"authorities": [
 *                {
 * 					"authority": "Buyer"
 *                }
 * 			],
 * 			"discriminatorValue": "Buyer",
 * 			"accountNonExpired": true,
 * 			"accountNonLocked": true,
 * 			"credentialsNonExpired": true
 *        },
 * 		"cart": [
 *            {
 * 				"id": 4,
 * 				"quantity": 1,
 * 				"product": {
 * 					"id": 1,
 * 					"name": "carne",
 * 					"volume": 1,
 * 					"price": 20.00,
 * 					"category": "CONGELADOS",
 * 					"seller": {
 * 						"id": 4,
 * 						"username": "seller",
 * 						"password": "$2a$10$QgOSNetrzdULkQze3ZVijOnLRGrKsRbOaGCbyJNI9ZtAWQrLOXuve",
 * 						"name": "V",
 * 						"lastName": "Fraga",
 * 						"cpf": 12345678901,
 * 						"enabled": true,
 * 						"authorities": [
 *                            {
 * 								"authority": "Seller"
 *                            }
 * 						],
 * 						"discriminatorValue": "Seller",
 * 						"accountNonExpired": true,
 * 						"accountNonLocked": true,
 * 						"credentialsNonExpired": true
 *                    }
 *                }
 *            },
 *            {
 * 				"id": 5,
 * 				"quantity": 5,
 * 				"product": {
 * 					"id": 2,
 * 					"name": "alface",
 * 					"volume": 1,
 * 					"price": 2.00,
 * 					"category": "FRESCOS",
 * 					"seller": {
 * 						"id": 5,
 * 						"username": "seller1",
 * 						"password": "$2a$10$QgOSNetrzdULkQze3ZVijOnLRGrKsRbOaGCbyJNI9ZtAWQrLOXuve",
 * 						"name": "Vi",
 * 						"lastName": "Fraga",
 * 						"cpf": 12345678901,
 * 						"enabled": true,
 * 						"authorities": [
 *                            {
 * 								"authority": "Seller"
 *                            }
 * 						],
 * 						"discriminatorValue": "Seller",
 * 						"accountNonExpired": true,
 * 						"accountNonLocked": true,
 * 						"credentialsNonExpired": true
 *                    }
 *                }
 *            }
 * 		]
 *    }
 * ]
 **/