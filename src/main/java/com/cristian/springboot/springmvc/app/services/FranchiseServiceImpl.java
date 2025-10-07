package com.cristian.springboot.springmvc.app.services;

import com.cristian.springboot.springmvc.app.entities.Branch;
import com.cristian.springboot.springmvc.app.entities.Franchise;
import com.cristian.springboot.springmvc.app.repositories.FranchiseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class FranchiseServiceImpl implements FranchiseService{

    final private FranchiseRepository repository;

    public FranchiseServiceImpl(FranchiseRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Franchise save(Franchise franchise) {
        return repository.save(franchise);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Franchise> findById(Long id) {
        return repository.findById(id);
    }
}
