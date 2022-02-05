package com.optily.assignment.entity.repository;

import com.optily.assignment.entity.Optimisation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 */
@Repository
public interface OptimisationRepository extends CrudRepository<Optimisation, Long> {


}
