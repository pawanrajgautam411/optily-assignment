package com.optily.assignment.entity.repository;

import com.optily.assignment.entity.Recommendation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 */
@Repository
public interface RecommendationRepository extends CrudRepository<Recommendation, Long> {


}
