package com.optily.assignment.service;

import com.optily.assignment.api.CampaignService;
import com.optily.assignment.boot.BeanFactory;
import com.optily.assignment.entity.Campaign;

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

}
