package com.concat.projetointegrador.repository;

import com.concat.projetointegrador.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.concat.projetointegrador.model.Sector;

import java.util.List;

@Repository
public interface SectorRepository extends JpaRepository<Sector, Long> {
    List<Sector> findByCategory(Category category);
}
