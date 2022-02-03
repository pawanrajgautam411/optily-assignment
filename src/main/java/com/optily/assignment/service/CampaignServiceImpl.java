package com.optily.assignment.service;

import com.optily.assignment.api.CampaignService;
import com.optily.assignment.boot.BeanFactory;
import com.optily.assignment.entity.Campaign;
import com.optily.assignment.entity.CampaignGroup;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 *
 */
public class CampaignServiceImpl implements CampaignService {


    /**
     * @return
     */
    @Override
    public List<Campaign> findAll() {
        Iterable<Campaign> iterable = BeanFactory.getCampaignRepository().findAll();
        return StreamSupport
                .stream(iterable.spliterator(), false)
                .collect(Collectors.toList());
    }

    /**
     * @param campaignGroupId
     * @return
     */
    @Override
    public List<Campaign> findByCampaignGroupId(long campaignGroupId) {
        CampaignGroup campaignGroup = new CampaignGroup();
        campaignGroup.setId(campaignGroupId);
        List<Campaign> byCampaignGroupId = BeanFactory.getCampaignRepository().findByCampaignGroupId(campaignGroup);

        if (byCampaignGroupId != null && byCampaignGroupId.size() > 0) {
            byCampaignGroupId.stream().forEach(campaign -> campaign.setCampaignGroup(null));
        }

        return byCampaignGroupId;
    }
}
