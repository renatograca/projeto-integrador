package com.concat.projetointegrador;

import com.concat.projetointegrador.model.SupervisorModel;
import com.concat.projetointegrador.service.SupervisorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SupervisorServiceTest {

    @Test
    public void validLengthNameSupervisor() {
        SupervisorService supervisorService = new SupervisorService();

        SupervisorModel newSupervisor = supervisorService.create(supervisor);

        Assertions.assertEquals(new SupervisorModel("super123", "Renato", "Graca"), newSupervisor);
    }
}
