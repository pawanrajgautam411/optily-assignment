package com.optily.assignment.api;

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
}
