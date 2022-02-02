package com.optily.assignment.api;

import com.optily.assignment.entity.CampaignGroup;

import java.io.File;
import java.io.InputStream;

/**
 *
 */
public interface CampaignGroupService {

    /**
     * @param file
     * @param campaignGroupName
     * @return
     */
    CampaignGroup uploadCSV(File file, String campaignGroupName);
}
