package com.concat.projetointegrador;

import com.concat.projetointegrador.model.Supervisor;
import com.concat.projetointegrador.repository.SupervisorRepository;
import com.concat.projetointegrador.service.SupervisorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class SupervisorServiceTest {

    @Test
    public void shouldCreateASupervisorWhenNameLengthBiggerThree() {
        SupervisorRepository supervisorRepository = Mockito.mock(SupervisorRepository.class);
        SupervisorService supervisorService = new SupervisorService(supervisorRepository);

        Mockito.when(supervisorRepository.save(Mockito.any(Supervisor.class))).thenReturn(createSupervisor());
        Supervisor newSupervisor = supervisorService.create(createSupervisor());

        Assertions.assertTrue(newSupervisor.getName().length() > 3);
    }

    @Test
    public void shouldReturnListOfSupervisor() {
        SupervisorRepository supervisorRepository = Mockito.mock(SupervisorRepository.class);
        SupervisorService supervisorService = new SupervisorService(supervisorRepository);

        List<Supervisor> supervisorList = new ArrayList<>();
        supervisorList.addAll(
                List.of(
                new Supervisor(1L, "Joao", "Coutinho"),
                new Supervisor(2L, "Juliana", "Brito"),
                new Supervisor(3L, "Andris", "Ceglys"),
                new Supervisor(4L, "Mellissa", "A"),
                new Supervisor(5L, "V", "Faria"),
                new Supervisor(6L, "Geovana", "Silva")
                )
        );
        Mockito.when(supervisorRepository.findAll()).thenReturn(supervisorList);


        List<Supervisor> supervisors = supervisorService.findAll();

        Assertions.assertEquals(supervisorList, supervisors);

    }

    private Supervisor createSupervisor() {
        return new Supervisor(123L, "Renato", "Graca");
    }
}
