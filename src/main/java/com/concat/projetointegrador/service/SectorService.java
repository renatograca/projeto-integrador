package com.concat.projetointegrador.service;

import com.concat.projetointegrador.model.Sector;
import com.concat.projetointegrador.repository.ISectorRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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
}
