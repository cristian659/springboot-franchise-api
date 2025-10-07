package com.cristian.springboot.springmvc.app.services;

import com.cristian.springboot.springmvc.app.entities.Branch;
import com.cristian.springboot.springmvc.app.entities.Franchise;
import com.cristian.springboot.springmvc.app.repositories.BranchRepository;
import com.cristian.springboot.springmvc.app.repositories.FranchiseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class BranchServiceImpl  implements BranchService{

    final private BranchRepository branchRepository;
    final private FranchiseService franchiseService;

    public BranchServiceImpl(BranchRepository branchRepository, FranchiseService franchiseService) {
        this.branchRepository = branchRepository;
        this.franchiseService = franchiseService;
    }

    @Override
    @Transactional
    public Branch save(Long idFranchise,Branch branch) {
        Optional<Franchise> franchiseOptional = franchiseService.findById(idFranchise);
        if(franchiseOptional.isPresent()){
            branch.setFranchise(franchiseOptional.get());
            return branchRepository.save(branch);
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Branch> findByID(Long id) {
        return branchRepository.findById(id);
    }
}
