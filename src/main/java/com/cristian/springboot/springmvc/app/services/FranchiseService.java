package com.cristian.springboot.springmvc.app.services;

import com.cristian.springboot.springmvc.app.entities.Franchise;

import java.util.Optional;

public interface FranchiseService {
    Franchise save(Franchise franchise);
    Optional<Franchise> findById(Long id);
}
