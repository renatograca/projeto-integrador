package com.concat.projetointegrador;

import com.concat.projetointegrador.model.SupervisorModel;
import com.concat.projetointegrador.repository.SupervisorRepository;
import com.concat.projetointegrador.service.SupervisorService;
import net.bytebuddy.implementation.bind.annotation.Super;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

public class SupervisorServiceTest {

    @Test
    public void createASupervisorWhenNameLengthBiggerThree() {
        SupervisorRepository supervisorRepository = Mockito.mock(SupervisorRepository.class);
        SupervisorService supervisorService = new SupervisorService(supervisorRepository);

        Mockito.when(supervisorRepository.save(Mockito.any(SupervisorModel.class))).thenReturn(createSupervisor());
        SupervisorModel newSupervisor = supervisorService.create(createSupervisor());

        Assertions.assertTrue(newSupervisor.getName().length() > 3);
    }

    private SupervisorModel createSupervisor() {
        return new SupervisorModel("super123", "Renato", "Graca");
    }
}
