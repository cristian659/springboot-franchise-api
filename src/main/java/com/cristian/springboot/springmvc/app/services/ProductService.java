package com.cristian.springboot.springmvc.app.services;

import com.cristian.springboot.springmvc.app.dto.MaxStonckProductDTO;
import com.cristian.springboot.springmvc.app.entities.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Optional<Product> findById(Long id);
    Product save(Product product);
    Product saveById(Long id, Product product);
    boolean delete(Long idBranchs, Long id);
    List<MaxStonckProductDTO>  getMaxStockProductsByFranchise(Long franchiseId);
}
