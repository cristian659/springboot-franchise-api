package com.cristian.springboot.springmvc.app.services;

import com.cristian.springboot.springmvc.app.dto.MaxStonckProductDTO;
import com.cristian.springboot.springmvc.app.entities.Branch;
import com.cristian.springboot.springmvc.app.entities.Franchise;
import com.cristian.springboot.springmvc.app.entities.Product;
import com.cristian.springboot.springmvc.app.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{
    final private ProductRepository repository;
    final private BranchService branchService;
    final private FranchiseService franchiseService;

    public ProductServiceImpl(ProductRepository repository, BranchService branchService, FranchiseService franchiseService) {
        this.repository = repository;
        this.branchService = branchService;
        this.franchiseService = franchiseService;
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<Product> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Product save(Product product) {
        return repository.save(product);
    }

    @Override
    @Transactional
    public Product saveById(Long idBranch ,Product product) {
        Optional<Branch> optionalBranch = branchService.findByID(idBranch);
        if(optionalBranch.isPresent()){
            product.setBranch(optionalBranch.orElseThrow());
            return repository.save(product);
        }

        return null;
    }

    @Override
    @Transactional
    public boolean delete(Long idBranchs, Long id) {
        Optional<Branch> optionalBranch = branchService.findByID(idBranchs);
        if(optionalBranch.isPresent()){
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    @Transactional(readOnly = true)
    public List<MaxStonckProductDTO> getMaxStockProductsByFranchise(Long franchiseId) {
        Optional<Franchise> franchiseOptional = franchiseService.findById(franchiseId);

        if(franchiseOptional.isEmpty()){
            return List.of();
        }

        Franchise franchise = franchiseOptional.get();

        return franchise.getBraches()
                .stream()
                .map(branch -> {
                    return branch.getProducts()
                            .stream()
                            .max(Comparator.comparingInt(Product::getStock))
                            .map(product -> new MaxStonckProductDTO(
                                    branch.getName(),
                                    product.getName(),
                                    product.getStock()
                            ))
                            .orElse(null);
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

    }


}
