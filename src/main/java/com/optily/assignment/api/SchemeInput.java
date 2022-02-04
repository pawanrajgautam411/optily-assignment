package com.optily.assignment.api;

import com.optily.assignment.entity.Campaign;
import com.optily.assignment.entity.CampaignGroup;

import java.util.List;

/**
 *
 */
public interface SchemeInput {

    /**
     * @return
     */
    static DefaultSchemeInputImpl defaultSchemeInput() {
        return new DefaultSchemeInputImpl();
    }

    /**
     * @return
     */
    CampaignGroup getCampaignGroup();

    /**
     * @return
     */
    List<Campaign> getCampaigns();
}
