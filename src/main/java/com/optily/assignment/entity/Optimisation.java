package com.optily.assignment.entity;

/**
 *
 */
public class Optimisation {
    private long id;
    private String status;
    private CampaignGroup campaignGroup;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CampaignGroup getCampaignGroup() {
        return campaignGroup;
    }

    public void setCampaignGroup(CampaignGroup campaignGroup) {
        this.campaignGroup = campaignGroup;
    }
}
