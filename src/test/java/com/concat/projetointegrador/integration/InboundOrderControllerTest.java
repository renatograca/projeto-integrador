package com.concat.projetointegrador.integration;

import com.concat.projetointegrador.dto.InboundOrderDTO;
import com.concat.projetointegrador.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class InboundOrderControllerTest {

		@Autowired
		private MockMvc mock;

		@Test
		@Sql({"/test-schema.sql"})
		public void shouldCreateAInboundOrder() throws Exception {
				ObjectMapper objectMapper = new ObjectMapper();
				objectMapper.registerModule(new JavaTimeModule());
				String payload = "{\"sector\":{\"sectorCode\":1,\"warehouseCode\":1},\"batchStock\":[{\"initialQuantity\":1,\"manufacturingDate\":\"2022-10-10\",\"manufacturingTime\":\"20:20:20\",\"dueDate\":\"2025-10-10\",\"initialTemperature\":2,\"productId\":1}]}";



				mock.perform(MockMvcRequestBuilders
								.post("/fresh-products/inboundorder")
								.contentType(MediaType.APPLICATION_JSON)
								.content(payload)
				).andExpect(MockMvcResultMatchers.status().isCreated());

				MvcResult result = mock.perform(MockMvcRequestBuilders
								.get("/fresh-products/inboundorder")
				).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

				String returnJson = result.getResponse().getContentAsString();


//				VendedorDto dto = new ObjectMapper().readValue(returnJson, VendedorDto.class);
//				assertEquals("333.123.111-10", dto.getCpf());

		}

		private static Optional<InboundOrder> inboundOrderMock(){
				return Optional.of(
								InboundOrder
												.builder()
												.id(1L)
												.sector(sectorMock())
												.batchStock(batchStockMock())
												.build());
		}

		private static Sector sectorMock() {
				return Sector
								.builder()
								.id(1L)
								.capacity(1)
								.warehouse(warehouseMock())
								.supervisor(supervisorMock())
								.category(Category.CONGELADOS)
								.build();
		}

		private static Warehouse warehouseMock() {
				return Warehouse
								.builder()
								.id(1L)
								.name("")
								.region("")
								.build();
		}

		private static Supervisor supervisorMock() {
				return Supervisor.builder()
								.id(1L)
								.name("")
								.lastName("")
								.build();
		}

		private static List<BatchStock> batchStockMock(){

				ArrayList<BatchStock> batchStocks = new ArrayList<>();
				InboundOrder inboundOrder = InboundOrder
								.builder()
								.id(1L)
								.sector(sectorMock())
								.batchStock(batchStocks)
								.build();

				BatchStock batchStock =
								BatchStock
												.builder()
												.id(1L)
												.currentQuantity(1)
												.initialQuantity(1)
												.initialTemperature(1)
												.currentTemperature(1)
												.product(productMock())
												.dueDate(LocalDate.now())
												.category(Category.CONGELADOS)
												.inboundOrder(inboundOrder)
												.manufacturingDate(LocalDate.now())
												.manufacturingTime(LocalTime.now())
												.build();

				batchStocks.add(batchStock);


				return batchStocks;
		}

		private static Product productMock() {
				return Product
								.builder()
								.id(1L)
								.name("")
								.volume(1)
								.seller(sellerMock())
								.price(BigDecimal.TEN)
								.category(Category.CONGELADOS)
								.build();
		}

		private static Seller sellerMock() {
				return Seller
								.builder()
								.id(1L)
								.name("")
								.lastName("")
								.build();
		}

}
