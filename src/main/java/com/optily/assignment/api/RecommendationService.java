package com.optily.assignment.api;

import com.optily.assignment.entity.Optimisation;
import com.optily.assignment.vo.RecommendationResponseVo;

/**
 *
 */
public interface RecommendationService {

    /**
     * @param campaignGroupId
     * @return
     */
    RecommendationResponseVo generate(long campaignGroupId);

    /**
     * @param campaignGroupId
     * @return
     */
    RecommendationResponseVo findByCampaignGroupId(long campaignGroupId);

    /**
     * @param campaignGroupId
     * @param optimisationType
     * @return
     */
    Optimisation applyOptimisation(long campaignGroupId, String optimisationType);

}
