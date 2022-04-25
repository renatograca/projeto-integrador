package com.concat.projetointegrador.repository;

import com.concat.projetointegrador.model.Sector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ISectorRepository extends JpaRepository<Sector, Long> {
    List<Sector> findAllByActiveTrue();
    Optional<Sector> findByIdAndActiveTrue(Long id);
}
