package com.optily.assignment.vo;

import com.optily.assignment.entity.Recommendation;

import java.util.List;

/**
 *
 */
public class RecommendationVo {
    private long campaign_group_id;
    private String campaign_group_name;
    private String optimisation_type;
    private List<Recommendation> recommendations;

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

    public String getOptimisation_type() {
        return optimisation_type;
    }

    public void setOptimisation_type(String optimisation_type) {
        this.optimisation_type = optimisation_type;
    }

    public List<Recommendation> getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(List<Recommendation> recommendations) {
        this.recommendations = recommendations;
    }
}
