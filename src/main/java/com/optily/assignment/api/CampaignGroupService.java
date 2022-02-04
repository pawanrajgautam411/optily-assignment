package com.optily.assignment.api;

import com.optily.assignment.entity.CampaignGroup;

import java.io.File;
import java.util.List;

/**
 *
 */
public interface CampaignGroupService {

    /**
     * @param file
     * @param campaignGroupName
     * @return
     */
    CampaignGroup createNow(File file, String campaignGroupName);

    /**
     * @return
     */
    List<CampaignGroup> findAll();


    CampaignGroup findById(long id);

}
