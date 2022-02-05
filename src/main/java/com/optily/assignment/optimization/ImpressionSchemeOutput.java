package com.optily.assignment.optimization;

import com.optily.assignment.api.SchemeOutput;
import com.optily.assignment.vo.RecommendCampaignVo;

/**
 *
 */
public class ImpressionSchemeOutput implements SchemeOutput {
    private RecommendCampaignVo recommendations;

    /**
     * @return
     */
    @Override
    public RecommendCampaignVo getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(RecommendCampaignVo recommendations) {
        this.recommendations = recommendations;
    }
}
