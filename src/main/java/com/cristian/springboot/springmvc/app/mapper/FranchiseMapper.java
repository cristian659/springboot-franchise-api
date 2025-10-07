package com.cristian.springboot.springmvc.app.mapper;

import com.cristian.springboot.springmvc.app.dto.BranchDTO;
import com.cristian.springboot.springmvc.app.dto.FranchiseDTO;
import com.cristian.springboot.springmvc.app.dto.ProductDTO;
import com.cristian.springboot.springmvc.app.entities.Branch;
import com.cristian.springboot.springmvc.app.entities.Franchise;
import com.cristian.springboot.springmvc.app.entities.Product;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FranchiseMapper {

    public static FranchiseDTO toFranchiseDTO(Franchise franchise){
        FranchiseDTO franchiseDTO = new FranchiseDTO();
        franchiseDTO.setId(franchise.getId());
        franchiseDTO.setName(franchise.getName());

        List<BranchDTO> branches = franchise.getBraches() != null
                ? franchise.getBraches().stream()
                .map(FranchiseMapper::toBranchDTO)
                .collect(Collectors.toList())
                : Collections.emptyList();

        return franchiseDTO;

    }

    public static BranchDTO toBranchDTO(Branch branch) {
        BranchDTO dto = new BranchDTO();
        dto.setId(branch.getId());
        dto.setName(branch.getName());

        List<ProductDTO> productDTOs = branch.getProducts() != null
                ? branch.getProducts().stream()
                .map(FranchiseMapper::toProductDTO)
                .collect(Collectors.toList())
                : Collections.emptyList();

        dto.setProducts(productDTOs);

        return dto;
    }

    public static ProductDTO toProductDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setStock(product.getStock());
        return dto;
    }
}
