package com.optily.assignment.vo;

import com.optily.assignment.entity.Optimisation;

import java.util.List;

/**
 *
 */
public class RecommendationResponseVo {
    private long campaign_group_id;
    private String campaign_group_name;
    private List<RecommendCampaignVo> recommendations;

    public long getCampaign_group_id() {
        return campaign_group_id;
    }

    public void setCampaign_group_id(long campaign_group_id) {
        this.campaign_group_id = campaign_group_id;
    }

    public String getCampaign_group_name() {
        return campaign_group_name;
    }

    public void setCampaign_group_name(String campaign_group_name) {
        this.campaign_group_name = campaign_group_name;
    }

    public List<RecommendCampaignVo> getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(List<RecommendCampaignVo> recommendations) {
        this.recommendations = recommendations;
    }
}
