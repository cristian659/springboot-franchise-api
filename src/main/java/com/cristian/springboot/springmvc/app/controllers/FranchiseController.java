package com.cristian.springboot.springmvc.app.controllers;

import com.cristian.springboot.springmvc.app.dto.FranchiseDTO;
import com.cristian.springboot.springmvc.app.entities.Franchise;
import com.cristian.springboot.springmvc.app.mapper.FranchiseMapper;
import com.cristian.springboot.springmvc.app.services.FranchiseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/franchise")
public class FranchiseController {

    private final FranchiseService franchiseService;

    public FranchiseController(FranchiseService franchiseService) {
        this.franchiseService = franchiseService;
    }

    /**
     * Funcion que encarga de crear una franquisia
     * @param f
     * @return
     */
    @PostMapping
    public ResponseEntity<FranchiseDTO> createFranchise(@RequestBody Franchise f) {
        Franchise saved = franchiseService.save(f);
        FranchiseDTO dto = FranchiseMapper.toFranchiseDTO(saved);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    /**
     * Funcion que obtiene las franquicias por ID
     * @param id
     * @return </FranchiseDTO>
     */
    @GetMapping("/{id}")
    public ResponseEntity<FranchiseDTO> getFranchise(@PathVariable Long id) {
        Optional<Franchise> franchise = franchiseService.findById(id);
        if (franchise.isPresent()) {
            FranchiseDTO dto = FranchiseMapper.toFranchiseDTO(franchise.get());
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Fucnion que se encarga de actualizar el nombre de una franquisia
     * @param id
     * @param franchise
     * @return FranchiseDto Actualizado
     */
    @PutMapping("/update/franchise/{id}")
    public ResponseEntity<FranchiseDTO> updateFranchise(@PathVariable Long id, @RequestBody Franchise franchise){
        Optional<Franchise> franchiseOptional = franchiseService.findById(id);

        if (franchiseOptional.isPresent()){
            Franchise existingFranchise = franchiseOptional.orElseThrow();
            existingFranchise.setName(franchise.getName());
            Franchise saved = franchiseService.save(existingFranchise);
            return ResponseEntity.status(HttpStatus.CREATED).body(FranchiseMapper.toFranchiseDTO(saved));
        }
        return ResponseEntity.notFound().build();
    }
}
