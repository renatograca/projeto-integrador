package com.concat.projetointegrador.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.concat.projetointegrador.model.*;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@Sql(value = {"/test-schema.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class InboundOrderControllerTest {

		@Autowired
		private MockMvc mock;

		@Test
		public void shouldCreateAInboundOrder() throws Exception {
				String payload = "{\"sector\":{\"sectorCode\":1,\"warehouseCode\":1},\"batchStock\":[{\"initialQuantity\":1,\"manufacturingDate\":\"2022-10-10\",\"manufacturingTime\":\"20:20:20\",\"dueDate\":\"2025-10-10\",\"initialTemperature\":2,\"productId\":1}]}";

				MvcResult result = mock.perform(post("/fresh-products/inboundorder")
												.contentType(MediaType.APPLICATION_JSON)
												.content(payload)
												.with(
																user("Supervisor")
																.password("123")
												)
								)
								.andExpect(MockMvcResultMatchers.status().isCreated())
								.andReturn();

				ObjectMapper objectMapper = new ObjectMapper();
				objectMapper.registerModule(new JavaTimeModule());
				objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
				objectMapper.setVisibility(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));

				String jsonReturned = result.getResponse().getContentAsString();
				InboundOrder inboundOrder = objectMapper.readValue(jsonReturned, InboundOrder.class);

				assertEquals(100, inboundOrder.getId());

		}

		@Test
		public void shouldFindAInboundOrderById() throws Exception {
				MvcResult result = mock.perform(get("/fresh-products/inboundorder/{id}", 99)
												.with(
																user("Supervisor")
																.password("123")
												)
								)
								.andExpect(MockMvcResultMatchers.status().isOk())
								.andReturn();

				ObjectMapper objectMapper = new ObjectMapper();
				objectMapper.registerModule(new JavaTimeModule());
				objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
				objectMapper.setVisibility(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));

				String jsonReturned = result.getResponse().getContentAsString();
				InboundOrder inboundOrder = objectMapper.readValue(jsonReturned, InboundOrder.class);

				assertEquals(99, inboundOrder.getId());

		}

		@Test
		public void shouldReturnAllInboundOrders() throws Exception {
				MvcResult result = mock.perform(get("/fresh-products/inboundorder", 99)
												.with(
																user("Supervisor")
																				.password("123")
												)
								)
								.andExpect(MockMvcResultMatchers.status().isOk())
								.andReturn();

				ObjectMapper objectMapper = new ObjectMapper();
				objectMapper.registerModule(new JavaTimeModule());
				objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
				objectMapper.setVisibility(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));

				String jsonReturned = result.getResponse().getContentAsString();
				List<InboundOrder> inboundOrder = objectMapper.readValue(jsonReturned, List.class);

				assertEquals( 1, inboundOrder.size());

		}

		@Test
		public void shouldUpdateAInboundOrderById() throws Exception {
				String payload = "{\"sector\":{\"sectorCode\":1,\"warehouseCode\":1},\"batchStock\":[{\"initialQuantity\":1,\"manufacturingDate\":\"2022-10-10\",\"manufacturingTime\":\"20:20:20\",\"dueDate\":\"2025-10-10\",\"initialTemperature\":23,\"currentQuantity\":23,\"initialTemperature\":23,\"productId\":1}]}";

				MvcResult result = mock.perform(
								put("/fresh-products/inboundorder/{id}", 99)
												.contentType(MediaType.APPLICATION_JSON)
												.content(payload)
												.with(
																user("Supervisor")
																.password("123")
												)
								)
								.andExpect(MockMvcResultMatchers.status().isCreated())
								.andReturn();

				ObjectMapper objectMapper = new ObjectMapper();
				objectMapper.registerModule(new JavaTimeModule());
				objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
				objectMapper.setVisibility(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));

				String jsonReturned = result.getResponse().getContentAsString();
				InboundOrder inboundOrder = objectMapper.readValue(jsonReturned, InboundOrder.class);

				assertEquals(23, inboundOrder.getBatchStock().get(0).getInitialTemperature());

		}

}


