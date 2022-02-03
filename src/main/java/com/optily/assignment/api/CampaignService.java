package com.optily.assignment.api;

import com.optily.assignment.entity.Campaign;

import java.util.List;

/**
 *
 */
public interface CampaignService {

    /*
     * @return
     */
    List<Campaign> findAll();


    /**
     * @param campaignGroupId
     * @return
     */
    List<Campaign> findByCampaignGroupId(long campaignGroupId);
}
