package com.cristian.springboot.springmvc.app.controllers;

import com.cristian.springboot.springmvc.app.dto.MaxStonckProductDTO;
import com.cristian.springboot.springmvc.app.dto.ProductDTO;
import com.cristian.springboot.springmvc.app.entities.Franchise;
import com.cristian.springboot.springmvc.app.entities.Product;
import com.cristian.springboot.springmvc.app.mapper.FranchiseMapper;
import com.cristian.springboot.springmvc.app.services.FranchiseService;
import com.cristian.springboot.springmvc.app.services.ProductService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.sound.sampled.Port;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    final private ProductService service;
    final private FranchiseService franchiseService;

    public ProductController(ProductService service, FranchiseService franchiseService) {
        this.service = service;
        this.franchiseService = franchiseService;
    }

    /**
     * Funcin
     * @param idBranch
     * @param product
     * @return
     */
    @PostMapping("/{idBranch}")
    public ResponseEntity<ProductDTO> create(@PathVariable Long idBranch, @RequestBody Product product){
        Product saved = service.saveById(idBranch, product);
        return ResponseEntity.status(HttpStatus.CREATED).body(FranchiseMapper.toProductDTO(saved));
    }

    /**
     *
     * @param idBranch
     * @param id
     * @return
     */
    @DeleteMapping("/{idBranch}/products/{id}")
    public ResponseEntity<Void> deleteProduct(
            @PathVariable Long idBranch,
            @PathVariable Long id) {

        boolean deleted = service.delete(idBranch, id);

        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     *
     * @param product
     * @param id
     * @return
     */
    @PutMapping("/{id}/products")
    public ResponseEntity<ProductDTO> updateProduct(@RequestBody Product product, @PathVariable Long id){
        Optional<Product> optionalProduct = service.findById(id);
        if(optionalProduct.isPresent()){
            Product productDb = optionalProduct.orElseThrow();
            productDb.setName(product.getName());
            productDb.setStock(product.getStock());
            Product saved = service.save(productDb);
            return ResponseEntity.status(HttpStatus.CREATED).body(FranchiseMapper.toProductDTO(saved));
        }
        return ResponseEntity.notFound().build();
    }

    /**
     *
     * @param idFranchise
     * @return
     */
    @GetMapping("/max-stock/{idFranchise}")
    public ResponseEntity<List<MaxStonckProductDTO>> findStockByFranchise(@PathVariable Long idFranchise){
        List<MaxStonckProductDTO> result = service.getMaxStockProductsByFranchise(idFranchise);

        if(result.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(result);
    }

}
