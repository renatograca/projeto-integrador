package com.concat.projetointegrador.repository;

import com.concat.projetointegrador.model.Sector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISectorRepository extends JpaRepository<Sector, Long> {
}
