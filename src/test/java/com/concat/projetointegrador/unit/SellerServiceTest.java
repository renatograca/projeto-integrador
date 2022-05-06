package com.concat.projetointegrador.unit;

import com.concat.projetointegrador.exception.EntityNotFound;
import com.concat.projetointegrador.model.*;
import com.concat.projetointegrador.repository.PurchasedOrderRepository;
import com.concat.projetointegrador.repository.SellerRepository;
import com.concat.projetointegrador.service.SellerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    private List<PurchasedOrder> purchasedOrders;
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
    public void whenFindBestSellersThenReturnAHashMapWithSellerIdAndSalesQuantity() {

        startPurchasedOrders();
        startSellersIdWithSalesQuantityHashMap();

        Mockito.when(purchasedOrderRepository.findAll()).thenReturn(purchasedOrders);

        HashMap<Long, Integer> response = service.findBestSellers();

        Assertions.assertEquals(response.getClass(), sellersSales.getClass());

    }

    private void startSellersIdWithSalesQuantityHashMap() {

        sellersSales = new HashMap();

        sellersSales.put(5L,6);
        sellersSales.put(4L,3);
        sellersSales.put(7L,1);

    }

    private void startPurchasedOrders() {

        Product product1 = Product.builder()
                .id(1L)
                .name("carne")
                .volume(1)
                .price(BigDecimal.valueOf(20.00))
                .category(Category.CONGELADOS)
                .seller(Seller.builder()
                        .id(4L)
                        .username("seller")
                        .password("$2a$10$QgOSNetrzdULkQze3ZVijOnLRGrKsRbOaGCbyJNI9ZtAWQrLOXuve")
                        .name("V")
                        .lastName("Fraga")
                        .build())
                .build();

        Product product2 = Product.builder()
                .id(3L)
                .name("frango")
                .volume(1)
                .price(BigDecimal.valueOf(10.00))
                .category(Category.CONGELADOS)
                .seller(Seller.builder()
                        .id(4L)
                        .username("seller")
                        .password("$2a$10$QgOSNetrzdULkQze3ZVijOnLRGrKsRbOaGCbyJNI9ZtAWQrLOXuve")
                        .name("V")
                        .lastName("Fraga")
                        .build())
                .build();

        Product product3 = Product.builder()
                .id(4L)
                .name("batata")
                .volume(1)
                .price(BigDecimal.valueOf(20.00))
                .category(Category.REFRIGERADOS)
                .seller(Seller.builder()
                        .id(7L)
                        .username("seller3")
                        .password("$2a$10$QgOSNetrzdULkQze3ZVijOnLRGrKsRbOaGCbyJNI9ZtAWQrLOXuve")
                        .name("Vini")
                        .lastName("Fraga")
                        .build())
                .build();

        Product product4 = Product.builder()
                .id(2L)
                .name("alface")
                .volume(1)
                .price(BigDecimal.valueOf(2.00))
                .category(Category.FRESCOS)
                .seller(Seller.builder()
                        .id(5L)
                        .username("seller1")
                        .password("$2a$10$QgOSNetrzdULkQze3ZVijOnLRGrKsRbOaGCbyJNI9ZtAWQrLOXuve")
                        .name("Vi")
                        .lastName("Fraga")
                        .build())
                .build();

        Product product5 = Product.builder()
                .id(1L)
                .name("carne")
                .volume(1)
                .price(BigDecimal.valueOf(20.00))
                .category(Category.CONGELADOS)
                .seller(Seller.builder()
                        .id(4L)
                        .username("seller")
                        .password("$2a$10$QgOSNetrzdULkQze3ZVijOnLRGrKsRbOaGCbyJNI9ZtAWQrLOXuve")
                        .name("V")
                        .lastName("Fraga")
                        .build())
                .build();

        Product product6 = Product.builder()
                .id(2L)
                .name("alface")
                .volume(1)
                .price(BigDecimal.valueOf(2.00))
                .category(Category.FRESCOS)
                .seller(Seller.builder()
                        .id(5L)
                        .username("seller1")
                        .password("$2a$10$QgOSNetrzdULkQze3ZVijOnLRGrKsRbOaGCbyJNI9ZtAWQrLOXuve")
                        .name("Vi")
                        .lastName("Fraga")
                        .build())
                .build();


        Cart cart1 = Cart.builder()
                .id(1L)
                .quantity(1)
                .product(product1)
                .purchasedOrder(PurchasedOrder.builder().status("fechado").build())
                .build();

        Cart cart2 = Cart.builder()
                .id(3L)
                .quantity(1)
                .product(product2)
                .purchasedOrder(PurchasedOrder.builder().status("fechado").build())
                .build();

        Cart cart3 = Cart.builder()
                .id(2L)
                .quantity(1)
                .product(product3)
                .purchasedOrder(PurchasedOrder.builder().status("fechado").build())
                .build();

        Cart cart4 = Cart.builder()
                .id(6L)
                .quantity(1)
                .product(product4)
                .purchasedOrder(PurchasedOrder.builder().status("fechado").build())
                .build();

        Cart cart5 = Cart.builder()
                .id(4L)
                .quantity(1)
                .product(product5)
                .purchasedOrder(PurchasedOrder.builder().status("fechado").build())
                .build();

        Cart cart6 = Cart.builder()
                .id(5L)
                .quantity(5)
                .product(product6)
                .purchasedOrder(PurchasedOrder.builder().status("fechado").build())
                .build();


        List<Cart> carts1 = new ArrayList();
        carts1.add(cart1);
        carts1.add(cart2);
        carts1.add(cart3);

        List<Cart> carts2 = new ArrayList();
        carts1.add(cart4);

        List<Cart> carts3 = new ArrayList();
        carts1.add(cart5);
        carts1.add(cart6);


        PurchasedOrder purchasedOrder1 = PurchasedOrder.builder()
                .id(1L)
                .status("fechado")
                .buyer(Buyer.builder()
                        .id(1L)
                        .username("buyer")
                        .password("$2a$10$z6ME137SMQ5Fsd7f5t8hj.Vx6JGrWdmXg6kqNsuuI/FdC6EAj2U26")
                        .name("Renatinho")
                        .lastName("Gracinha")
                        .cpf(12345478911L)
                        .build())
                .cart(carts1)
                .build();

        PurchasedOrder purchasedOrder2 = PurchasedOrder.builder()
                .id(3L)
                .status("fechado")
                .buyer(Buyer.builder()
                        .id(1L)
                        .username("buyer")
                        .password("$2a$10$z6ME137SMQ5Fsd7f5t8hj.Vx6JGrWdmXg6kqNsuuI/FdC6EAj2U26")
                        .name("Renatinho")
                        .lastName("Gracinha")
                        .cpf(12345478911L)
                        .build())
                .cart(carts2)
                .build();

        PurchasedOrder purchasedOrder3 = PurchasedOrder.builder()
                .id(2L)
                .status("fechado")
                .buyer(Buyer.builder()
                        .id(1L)
                        .username("buyer")
                        .password("$2a$10$z6ME137SMQ5Fsd7f5t8hj.Vx6JGrWdmXg6kqNsuuI/FdC6EAj2U26")
                        .name("Renatinho")
                        .lastName("Gracinha")
                        .cpf(12345478911L)
                        .build())
                .cart(carts3)
                .build();

        purchasedOrders = new ArrayList();
        purchasedOrders.add(purchasedOrder1);
        purchasedOrders.add(purchasedOrder2);
        purchasedOrders.add(purchasedOrder3);


    }

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