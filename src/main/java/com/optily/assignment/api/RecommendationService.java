package com.optily.assignment.api;

import com.optily.assignment.vo.RecommendationVo;

/**
 *
 */
public interface RecommendationService {

    /**
     * @param campaignGroupId
     * @return
     */
    RecommendationVo findByCampaignGroupId(long campaignGroupId);
}
