package com.concat.projetointegrador.repository;

import com.concat.projetointegrador.model.SupervisorModel;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

public interface SupervisorRepository extends JpaRepositoryImplementation<SupervisorModel, String> {
}
