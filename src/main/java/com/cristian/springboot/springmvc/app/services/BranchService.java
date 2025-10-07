package com.cristian.springboot.springmvc.app.services;

import com.cristian.springboot.springmvc.app.entities.Branch;

import java.util.Optional;

public interface BranchService {
    Branch save(Long idFranchise, Branch branch);
    Optional<Branch> findByID(Long id);
}
