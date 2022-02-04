package com.optily.assignment.entity.repository;

import com.optily.assignment.entity.Optimisation;
import com.optily.assignment.entity.Recommendation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 */
@Repository
public interface RecommendationRepository extends CrudRepository<Recommendation, Long> {

    /**
     * @param optimisation
     * @return
     */
    @Query("select rmd from Recommendation rmd where rmd.optimisation = :optimisation")
    List<Recommendation> findByOptimisationId(Optimisation optimisation);

}
