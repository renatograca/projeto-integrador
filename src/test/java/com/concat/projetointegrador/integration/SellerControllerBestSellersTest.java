package com.concat.projetointegrador.integration;

import com.concat.projetointegrador.dto.BestSellerDTO;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@Sql(value = {"/bestSellerSchema.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class SellerControllerBestSellersTest {

    @Autowired
    private MockMvc mock;

    @Test
    public void ShouldReturnAListOfBestSellersDTOOrderedByQuantityOfSales() throws Exception {

        SimpleGrantedAuthority supervisor = new SimpleGrantedAuthority("Supervisor");
        ArrayList<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
        simpleGrantedAuthorities.add(supervisor);

        MvcResult result = mock.perform(get("/seller/bests")
                .with(
                        user("Supervisor")
                                .password("123")
                                .authorities(simpleGrantedAuthorities)
                )
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.setVisibility(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));

//        String jsonReturned = result.getResponse().getContentAsString();
//        List<BestSellerDTO> bestSellersDTO = objectMapper.readValue(jsonReturned, List.class);
        String response = result.getResponse().getContentAsString();
        assertEquals("[{\"id\":5,\"username\":\"seller1\",\"quantityOfProductsSale\":6},{\"id\":4,\"username\":\"seller\",\"quantityOfProductsSale\":3},{\"id\":7,\"username\":\"seller3\",\"quantityOfProductsSale\":1}]", response);
//        assertEquals(6, bestSellersDTO.stream().findFirst().get().getQuantityOfProductsSale());

    }

}


