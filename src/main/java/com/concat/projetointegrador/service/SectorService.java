package com.concat.projetointegrador.service;

import com.concat.projetointegrador.dto.SectorRequestDTO;
import com.concat.projetointegrador.model.Sector;
import com.concat.projetointegrador.repository.SectorRepository;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class SectorService {

    private final SectorRepository repository;

    public SectorService(SectorRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Sector save(Sector sector) {
        return repository.save(sector);
    }

    public List<Sector> findAll() {
        return repository.findAll();
    }

    public Sector findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Setor não encontrado! Tente outro ID ou crie um novo Setor!"));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Sector findByProductId(Long id) {
        return findById(id);
    }
}
