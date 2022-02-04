package com.optily.assignment.api;

import com.optily.assignment.entity.Campaign;
import com.optily.assignment.entity.CampaignGroup;

import java.util.List;

/**
 *
 */
public class DefaultSchemeInputImpl implements SchemeInput {
    private CampaignGroup campaignGroup;
    private List<Campaign> campaigns;

    /**
     * @return
     */
    public CampaignGroup getCampaignGroup() {
        return campaignGroup;
    }

    public void setCampaignGroup(CampaignGroup campaignGroup) {
        this.campaignGroup = campaignGroup;
    }

    /**
     * @return
     */
    public List<Campaign> getCampaigns() {
        return campaigns;
    }

    public void setCampaigns(List<Campaign> campaigns) {
        this.campaigns = campaigns;
    }
}
