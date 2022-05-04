package com.concat.projetointegrador.integration;

import com.concat.projetointegrador.repository.ProductRepository;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql({"/test-schema.sql"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private String payloadProduct() {
        return "{\"id\":2,\"name\":\"Asinha de frango\",\"volume\":10,\"price\":11.0,\"category\":\"FRESCOS\",\"seller\":{\"id\": 1}}";
    }

    @Test
    public void shouldCreateAProductAndReturn201() throws Exception {
                mockMvc.perform(MockMvcRequestBuilders.post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payloadProduct()))
                .andExpect(status().isCreated())
                .andExpect(content().contentType("application/json"))
                .andReturn();
    }

    @Test
    public void shouldFindByCategoryAndReturn200() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/products/category/{category}", "CONGELADOS"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        String response = result.getResponse().getContentAsString();
        assertEquals("[{\"name\":\"frango\",\"volume\":1,\"price\":20.00,\"category\":\"CONGELADOS\"}]", response);
    }

    @Test
    public void shouldFindAllAndReturn200() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/products"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        String response = result.getResponse().getContentAsString();
        assertEquals("[{\"name\":\"frango\",\"volume\":1,\"price\":20.00,\"category\":\"CONGELADOS\"}]", response);
    }

    @Test
    public void shouldFindByIdAndReturn200() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/products/{id}", "1" ))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        String response = result.getResponse().getContentAsString();
        assertEquals("{\"productId\":1,\"sector\":[{\"sectorCode\":1,\"warehouseCode\":1}],\"batchStock\":[" +
                "{\"initialQuantity\":10,\"currentQuantity\":10,\"manufacturingDate\":\"2022-04-20\",\"manufacturingTime\"" +
                ":\"20:00:00\",\"dueDate\":\"2023-10-10\",\"initialTemperature\":9,\"productId\":1}]}", response);
    }

}
