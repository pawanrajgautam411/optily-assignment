package com.optily.assignment.api;

import com.optily.assignment.entity.Optimisation;

import java.util.List;

/**
 *
 */
public interface OptimisationService {

    /**
     * @param campaignGroupId
     * @return
     */
    List<Optimisation> findByCampaignGroupId(long campaignGroupId);

    /**
     * @param id
     * @return
     */
    Optimisation findById(long id);
}
