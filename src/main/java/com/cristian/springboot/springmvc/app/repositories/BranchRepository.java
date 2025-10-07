package com.cristian.springboot.springmvc.app.repositories;

import com.cristian.springboot.springmvc.app.entities.Branch;
import org.springframework.data.repository.CrudRepository;

public interface BranchRepository extends CrudRepository<Branch, Long> {
}
