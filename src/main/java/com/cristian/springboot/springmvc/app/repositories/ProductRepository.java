package com.cristian.springboot.springmvc.app.repositories;

import com.cristian.springboot.springmvc.app.entities.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
