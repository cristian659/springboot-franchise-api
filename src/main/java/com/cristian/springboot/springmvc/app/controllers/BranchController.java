package com.cristian.springboot.springmvc.app.controllers;

import com.cristian.springboot.springmvc.app.dto.BranchDTO;
import com.cristian.springboot.springmvc.app.dto.ProductDTO;
import com.cristian.springboot.springmvc.app.entities.Branch;
import com.cristian.springboot.springmvc.app.entities.Franchise;
import com.cristian.springboot.springmvc.app.entities.Product;
import com.cristian.springboot.springmvc.app.mapper.FranchiseMapper;
import com.cristian.springboot.springmvc.app.repositories.BranchRepository;
import com.cristian.springboot.springmvc.app.services.FranchiseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api/branches")
public class BranchController {

    final private BranchRepository repository;

    final private FranchiseService franchiseService;

    public BranchController(BranchRepository repository, FranchiseService franchiseService) {
        this.repository = repository;
        this.franchiseService = franchiseService;
    }

    /**
     *
     * @param idFranchise
     * @param branch
     * @return
     */
    @PostMapping("/{idFranchise}/franchise")
    public ResponseEntity addBranch(@PathVariable Long idFranchise, @RequestBody Branch branch){
        Optional<Franchise> franchiseOptional = franchiseService.findById(idFranchise);

        if(franchiseOptional.isPresent()){
            branch.setFranchise(franchiseOptional.get());
            Branch saved = repository.save(branch);
            return ResponseEntity.ok(FranchiseMapper.toBranchDTO(saved));
        }

        return ResponseEntity.notFound().build();
    }

    /**
     * Funcion que se encarga de actualizar el nombre de la sucursar
     * @param id
     * @param branch
     * @return
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<BranchDTO> updateNameBranch(@PathVariable Long id, @RequestBody Branch branch){
        Optional<Branch> branchOptional = repository.findById(id);
        if(branchOptional.isPresent()){
            Branch existingBranch = branchOptional.orElseThrow();
            existingBranch.setName(branch.getName());
            Branch saved = repository.save(existingBranch);
            return ResponseEntity.status(HttpStatus.CREATED).body(FranchiseMapper.toBranchDTO(saved));
        }
        return ResponseEntity.notFound().build();
    }


}
