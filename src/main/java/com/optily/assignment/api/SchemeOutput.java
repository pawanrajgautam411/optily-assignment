package com.optily.assignment.api;

import com.optily.assignment.entity.Recommendation;

import java.util.List;

/**
 *
 */
public interface SchemeOutput {

    /**
     * @return
     */
    List<Recommendation> getRecommendations();
}
