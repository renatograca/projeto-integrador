package com.concat.projetointegrador.service;

import com.concat.projetointegrador.exception.EntityNotFound;
import com.concat.projetointegrador.model.Category;
import com.concat.projetointegrador.model.Sector;
import com.concat.projetointegrador.repository.SectorRepository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SectorService {

    private final SectorRepository repository;

    public Sector findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFound("Setor não encontrado! Tente outro ID ou crie um novo Setor!"));
    }

    public List<Sector> findByCategory(Category category) {
        return repository.findByCategory(category);
    }
}
