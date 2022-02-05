package com.optily.assignment.api;

import com.optily.assignment.entity.Optimisation;
import com.optily.assignment.vo.RecommendationResponseVo;

import java.util.List;

/**
 *
 */
public interface OptimisationService {

    /**
     * @param campaignGroupId
     * @param optimisationType
     * @return
     */
    RecommendationResponseVo applyOptimisation(long campaignGroupId, String optimisationType);

    /**
     * @param campaignGroupId
     * @return
     */
    List<Optimisation> findByCampaignGroupId(long campaignGroupId);

    /**
     * @param id
     * @return
     */
    Optimisation findById(long id);
}
