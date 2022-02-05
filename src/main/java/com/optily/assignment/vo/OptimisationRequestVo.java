package com.optily.assignment.vo;

/**
 *
 */
public class OptimisationRequestVo {
    private long campaign_group_id;
    private String optimisation_type;

    public long getCampaign_group_id() {
        return campaign_group_id;
    }

    public void setCampaign_group_id(long campaign_group_id) {
        this.campaign_group_id = campaign_group_id;
    }

    public String getOptimisation_type() {
        return optimisation_type;
    }

    public void setOptimisation_type(String optimisation_type) {
        this.optimisation_type = optimisation_type;
    }
}
