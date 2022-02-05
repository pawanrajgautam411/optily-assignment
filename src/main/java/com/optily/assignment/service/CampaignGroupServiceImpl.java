package com.optily.assignment.service;

import com.optily.assignment.api.CampaignGroupService;
import com.optily.assignment.boot.RepositoryBeanFactory;
import com.optily.assignment.entity.CampaignGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 *
 */
public class CampaignGroupServiceImpl implements CampaignGroupService {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(CampaignGroupServiceImpl.class);

    /**
     * @param file
     * @param campaignGroupName
     * @return
     */
    @Override
    public CampaignGroup createNow(File file, String campaignGroupName) {
        return new ActionCreateCampaignGroup().createNow(file, campaignGroupName);
    }

    /**
     * @return
     */
    @Override
    public List<CampaignGroup> findAll() {
        Iterable<CampaignGroup> iterable = RepositoryBeanFactory.getCampaignGroupRepository().findAll();
        return StreamSupport.stream(iterable.spliterator(), false)
                .collect(Collectors.toList());
    }

    /**
     * @param id
     * @return
     */
    @Override
    public CampaignGroup findById(long id) {
        Optional<CampaignGroup> optional = RepositoryBeanFactory.getCampaignGroupRepository()
                .findById(id);

        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }
}
