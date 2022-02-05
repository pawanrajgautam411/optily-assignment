package com.optily.assignment.vo;

import com.optily.assignment.entity.Recommendation;

import java.util.List;

/**
 *
 */
public class RecommendCampaignVo {
    private String optimisation_type;
    private List<Recommendation> campaigns;

    public String getOptimisation_type() {
        return optimisation_type;
    }

    public void setOptimisation_type(String optimisation_type) {
        this.optimisation_type = optimisation_type;
    }

    public List<Recommendation> getCampaigns() {
        return campaigns;
    }

    public void setCampaigns(List<Recommendation> campaigns) {
        this.campaigns = campaigns;
    }
}
