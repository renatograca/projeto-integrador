package com.concat.projetointegrador.integration;

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
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql({"/test-schema.sql"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class PurchaseOrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private String payload() {
        return "{\"buyer\":{\"id\":4},\"status\":\"aberto\",\"cart\":[{\"products\":{\"id\": 1},\"quantity\": 5}]}";
    }

    @Test
    public void shouldCreateAPurchaseOrderAndReturn201() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .with(user("Buyer").password("123"))
                .content(payload()))
                .andExpect(status().isCreated())
                .andExpect(content().contentType("application/json"))
                .andReturn();
    }

    @Test
    public void shouldFindByIdAndReturn200() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/orders")
                .with(user("Buyer").password("123"))
                .param("id", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        String response = result.getResponse().getContentAsString();
        assertEquals("{\"id\":1,\"date\":\"2022-04-20\",\"status\":\"aberto\",\"buyer\":{\"id\":1,\"username\":\"ADMIN\",\"password\":\"$2a$10$81VuVXFi5JmfOdCblgjj0ODqsn11TUXfUEYnm.jInkbUIcd9xx31u\",\"name\":\"ADMIN\",\"lastName\":\"ADMIN\",\"cpf\":null,\"enabled\":true,\"discriminatorValue\":\"Buyer\",\"accountNonExpired\":true,\"accountNonLocked\":true,\"credentialsNonExpired\":true,\"authorities\":[{\"authority\":\"Buyer\"}]},\"cart\":[{\"id\":1,\"quantity\":10,\"products\":{\"id\":1,\"name\":\"frango\",\"volume\":1,\"price\":20.00,\"category\":\"CONGELADOS\",\"seller\":{\"id\":3,\"username\":\"Seller\",\"password\":\"$2a$10$81VuVXFi5JmfOdCblgjj0ODqsn11TUXfUEYnm.jInkbUIcd9xx31u\",\"name\":\"Seller\",\"lastName\":\"Seller\",\"cpf\":null,\"enabled\":true,\"discriminatorValue\":\"Seller\",\"accountNonExpired\":true,\"accountNonLocked\":true,\"credentialsNonExpired\":true,\"authorities\":[{\"authority\":\"Seller\"}]}}}]}", response);
    }

    @Test
    public void shouldUpdateAndReturn200() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/orders")
                .with(user("Buyer").password("123"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload())
                .param("id", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        String response = result.getResponse().getContentAsString();
        assertEquals("{\"id\":1,\"date\":\"2022-04-20\",\"status\":\"finalizado\",\"buyer\":{\"id\":1,\"username\":\"ADMIN\",\"password\":\"$2a$10$81VuVXFi5JmfOdCblgjj0ODqsn11TUXfUEYnm.jInkbUIcd9xx31u\",\"name\":\"ADMIN\",\"lastName\":\"ADMIN\",\"cpf\":null,\"enabled\":true,\"discriminatorValue\":\"Buyer\",\"accountNonExpired\":true,\"accountNonLocked\":true,\"credentialsNonExpired\":true,\"authorities\":[{\"authority\":\"Buyer\"}]},\"cart\":[{\"id\":1,\"quantity\":10,\"products\":{\"id\":1,\"name\":\"frango\",\"volume\":1,\"price\":20.00,\"category\":\"CONGELADOS\",\"seller\":{\"id\":3,\"username\":\"Seller\",\"password\":\"$2a$10$81VuVXFi5JmfOdCblgjj0ODqsn11TUXfUEYnm.jInkbUIcd9xx31u\",\"name\":\"Seller\",\"lastName\":\"Seller\",\"cpf\":null,\"enabled\":true,\"discriminatorValue\":\"Seller\",\"accountNonExpired\":true,\"accountNonLocked\":true,\"credentialsNonExpired\":true,\"authorities\":[{\"authority\":\"Seller\"}]}}}]}", response);
    }
}
