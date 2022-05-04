package com.concat.projetointegrador.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@Sql(value = {"/test-schema.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class SupervisorControllerService {

    @Autowired
    private MockMvc mockMvc;

    private String payload() {
        return "{\"name\":\"joao\",\"lastName\": \"Silva\",\"cpf\": 12345478911,\"username\": \"jabcnetoBUY1\",\"password\": 123}";
    }

    @Test
    public void shouldCreateABuyerAndReturn201() throws Exception {
        SimpleGrantedAuthority supervisor = new SimpleGrantedAuthority("ADMIN");
        ArrayList<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();

        simpleGrantedAuthorities.add(supervisor);
        mockMvc.perform(MockMvcRequestBuilders.post("/supervisor")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload())
                .with(user("ADMIN").password("123").authorities(simpleGrantedAuthorities)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType("application/json"))
                .andReturn();
    }
}
