package com.concat.projetointegrador.integration;

import com.concat.projetointegrador.dto.WarehouseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.concat.projetointegrador.repository.WarehouseRepository;
import org.junit.jupiter.api.BeforeEach;
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

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class WarehouseControllerTest {

    @Autowired
    private static MockMvc mockMvc;

    @Autowired
    private WarehouseRepository warehouseRepository;

    @BeforeEach
    void authorized() throws Exception {
        token = String.valueOf(mockMvc
                .perform(MockMvcRequestBuilders.post("/login")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("username", "geovanna")
                .param("password","123"))
                .andDo(print()));
    }
    private static String token;

    private String getObject() {
        return "{\"name\":\"armazem frios\", \"region\":\"melhor regiao\"}";
    }

    private void createWarehouse() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.post("/warehouse")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getObject()))
                .andExpect(status().isCreated())
                .andExpect(content().contentType("application/json"))
                .andReturn();
    }

    @Test
    @Sql({"/test-schema.sql"})
    public void shouldCreateAWarehouseAndReturn201() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.post("/warehouse")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getObject()))
                .andExpect(status().isCreated())
                .andExpect(content().contentType("aplication/json"))
                .andReturn();

        String response = mvcResult.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        WarehouseDTO warehouseDTO = objectMapper.readValue(response, WarehouseDTO.class);
        warehouseRepository.deleteById(warehouseDTO.getId());
    }
}
