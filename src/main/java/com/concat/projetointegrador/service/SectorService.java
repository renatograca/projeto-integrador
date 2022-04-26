package com.concat.projetointegrador.service;

import com.concat.projetointegrador.model.Sector;
import com.concat.projetointegrador.repository.ISectorRepository;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class SectorService {

    private final ISectorRepository repository;

    public SectorService(ISectorRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Sector save(Sector sector) {
        return repository.save(sector);
    }

    public List<Sector> findAll() {
        return repository.findAllByActiveTrue();
    }

    public Sector findById(Long id) {
        return repository.findByIdAndActiveTrue(id).orElseThrow(() -> new RuntimeException("Setor não encontrado! Tente outro ID ou crie um novo Setor!"));
    }

    public void delete(Long id) {
        Sector sector = findById(id);
        sector.setActive(false);
        repository.save(sector);
    }
}
