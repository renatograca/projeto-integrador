package com.concat.projetointegrador.service;

import com.concat.projetointegrador.model.Sector;
import com.concat.projetointegrador.repository.ISectorRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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

    public Optional<Sector> findById(Long id) {
        return repository.findByIdAndActiveTrue(id);
    }

    public Boolean delete(Long id) {
        Optional<Sector> sectorOptional = findById(id);
        if (sectorOptional.isEmpty()) {
            return false;
        }

        sectorOptional.get().setActive(false);
        repository.save(sectorOptional.get());
        return true;
    }
}
