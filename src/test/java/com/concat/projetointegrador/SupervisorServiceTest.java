package com.concat.projetointegrador;

import com.concat.projetointegrador.model.SupervisorModel;
import com.concat.projetointegrador.repository.SupervisorRepository;
import com.concat.projetointegrador.service.SupervisorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class SupervisorServiceTest {

    @Test
    public void createASupervisorWhenNameLengthBiggerThree() {
        SupervisorRepository supervisorRepository = Mockito.mock(SupervisorRepository.class);
        SupervisorService supervisorService = new SupervisorService(supervisorRepository);

        Mockito.when(supervisorRepository.save(Mockito.any(SupervisorModel.class))).thenReturn(createSupervisor());
        SupervisorModel newSupervisor = supervisorService.create(createSupervisor());

        Assertions.assertTrue(newSupervisor.getName().length() > 3);
    }

    @Test
    public void returnListOfSupervisor() {
        SupervisorRepository supervisorRepository = Mockito.mock(SupervisorRepository.class);
        SupervisorService supervisorService = new SupervisorService(supervisorRepository);

        List<SupervisorModel> supervisorModelList = new ArrayList<>();
        supervisorModelList.addAll(
                List.of(
                new SupervisorModel(1L, "Joao", "Coutinho"),
                new SupervisorModel(2L, "Juliana", "Brito"),
                new SupervisorModel(3L, "Andris", "Ceglys"),
                new SupervisorModel(4L, "Mellissa", "A"),
                new SupervisorModel(5L, "V", "Faria"),
                new SupervisorModel(6L, "Geovana", "Silva")
                )
        );
        Mockito.when(supervisorRepository.findAll()).thenReturn(supervisorModelList);


        List<SupervisorModel> supervisorModels = supervisorService.list();

        Assertions.assertEquals(supervisorModelList, supervisorModels);

    }

    private SupervisorModel createSupervisor() {
        return new SupervisorModel(123L, "Renato", "Graca");
    }
}
