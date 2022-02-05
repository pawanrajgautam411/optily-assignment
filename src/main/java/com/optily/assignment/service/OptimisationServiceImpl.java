package com.optily.assignment.service;

import com.optily.assignment.api.OptimisationService;
import com.optily.assignment.boot.RepositoryBeanFactory;
import com.optily.assignment.entity.CampaignGroup;
import com.optily.assignment.entity.Optimisation;

import java.util.List;
import java.util.Optional;

/**
 *
 */
public class OptimisationServiceImpl implements OptimisationService {
    
    /**
     * @param campaignGroupId
     * @return
     */
    @Override
    public List<Optimisation> findByCampaignGroupId(long campaignGroupId) {
        Optional<CampaignGroup> campaignGroup = RepositoryBeanFactory.getCampaignGroupRepository()
                .findById(campaignGroupId);

        if (campaignGroup.isPresent()) {
            List<Optimisation> optimisations = campaignGroup.get().getOptimisations();
            if (optimisations != null && optimisations.size() > 0) {
                optimisations.forEach(optimisation -> optimisation.setRecommendations(null));
            }
            return optimisations;
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
            Optimisation optimisation = optional.get();
            if (optimisation != null) {
                optimisation.setRecommendations(null);
            }
            return optimisation;
        }
        return null;
    }
}
