package com.cristian.springboot.springmvc.app.repositories;

import com.cristian.springboot.springmvc.app.entities.Franchise;
import org.springframework.data.repository.CrudRepository;

public interface FranchiseRepository extends CrudRepository<Franchise, Long> {
}
