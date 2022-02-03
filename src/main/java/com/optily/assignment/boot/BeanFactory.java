package com.optily.assignment.boot;

import com.optily.assignment.entity.repository.CampaignGroupRepository;
import com.optily.assignment.entity.repository.CampaignRepository;

/**
 *
 */
public class BeanFactory {
    private static CampaignGroupRepository campaignGroupRepository;
    private static CampaignRepository campaignRepository;

    private BeanFactory() {
    }

    /**
     * @return
     */
    public static CampaignGroupRepository getCampaignGroupRepository() {
        return campaignGroupRepository;
    }

    protected static void setCampaignGroupRepository(CampaignGroupRepository campaignGroupRepository) {
        BeanFactory.campaignGroupRepository = campaignGroupRepository;
    }

    /**
     * @return
     */
    public static CampaignRepository getCampaignRepository() {
        return campaignRepository;
    }

    protected static void setCampaignRepository(CampaignRepository campaignRepository) {
        BeanFactory.campaignRepository = campaignRepository;
    }
}
