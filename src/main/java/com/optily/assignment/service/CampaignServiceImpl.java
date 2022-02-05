package com.optily.assignment.service;

import com.optily.assignment.api.CampaignService;
import com.optily.assignment.boot.RepositoryBeanFactory;
import com.optily.assignment.entity.Campaign;
import com.optily.assignment.entity.CampaignGroup;

import java.util.List;
import java.util.Optional;
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
        Iterable<Campaign> iterable = RepositoryBeanFactory.getCampaignRepository().findAll();
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
        Optional<CampaignGroup> campaignGroup = RepositoryBeanFactory.getCampaignGroupRepository()
                .findById(campaignGroupId);

        if (campaignGroup.isPresent()) {
            return campaignGroup.get().getCampaigns();
        }

        return null;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Campaign findById(long id) {
        Optional<Campaign> optional = RepositoryBeanFactory.getCampaignRepository()
                .findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }
}
