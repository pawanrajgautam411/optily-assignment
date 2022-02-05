package com.optily.assignment.service;

import com.optily.assignment.api.OptimisationService;
import com.optily.assignment.boot.RepositoryBeanFactory;
import com.optily.assignment.entity.CampaignGroup;
import com.optily.assignment.entity.Optimisation;
import com.optily.assignment.vo.RecommendationResponseVo;

import java.util.List;
import java.util.Optional;

/**
 *
 */
public class OptimisationServiceImpl implements OptimisationService {

    /**
     * @param campaignGroupId
     * @param optimisation_type
     * @return
     */
    @Override
    public RecommendationResponseVo applyOptimisation(long campaignGroupId,
                                                      String optimisation_type) {

        return new ActionApplyOptimisation().applyNow(campaignGroupId, optimisation_type);
    }

    /**
     * @param campaignGroupId
     * @return
     */
    @Override
    public List<Optimisation> findByCampaignGroupId(long campaignGroupId) {
        Optional<CampaignGroup> campaignGroup = RepositoryBeanFactory.getCampaignGroupRepository()
                .findById(campaignGroupId);

        if (campaignGroup.isPresent()) {
            return campaignGroup.get().getOptimisations();
        }
        return null;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Optimisation findById(long id) {
        Optional<Optimisation> optional = RepositoryBeanFactory.getOptimisationRepository()
                .findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }
}
