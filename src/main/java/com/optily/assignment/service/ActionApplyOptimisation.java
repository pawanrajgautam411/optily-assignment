package com.optily.assignment.service;

import com.optily.assignment.boot.RepositoryBeanFactory;
import com.optily.assignment.entity.Campaign;
import com.optily.assignment.entity.CampaignGroup;
import com.optily.assignment.entity.Optimisation;
import com.optily.assignment.exception.ApplyOptimisationFailedException;
import com.optily.assignment.optimization.OptimisationType;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *
 */
class ActionApplyOptimisation {

    /**
     *
     */
    protected ActionApplyOptimisation() {
    }

    /**
     * @param campaignGroupId
     * @param optimisation_type
     * @return
     */
    protected Optimisation applyNow(long campaignGroupId,
                                    String optimisation_type) {

        if (campaignGroupId <= 0) {
            throw new IllegalArgumentException("invalid-campaign-group-id");
        }

        if (optimisation_type == null || optimisation_type.trim().length() <= 0) {
            throw new IllegalArgumentException("invalid-optimisation-type");

        } else if (optimisation_type.trim().equalsIgnoreCase(OptimisationType.Click_Based_Optimisation.name())
                || optimisation_type.trim().equalsIgnoreCase(OptimisationType.Conversion_Based_Optimisation.name())) {
            throw new IllegalArgumentException("optimisation-type (" + optimisation_type + ") is-only-available-for-DEMO");
        }

        Optional<CampaignGroup> optionalCampaignGroup = RepositoryBeanFactory
                .getCampaignGroupRepository().findById(campaignGroupId);

        if (!optionalCampaignGroup.isPresent()) {
            throw new IllegalArgumentException("campaign-group-not-found-with-id ("
                    + campaignGroupId + ")");
        }

        CampaignGroup campaignGroupDB = optionalCampaignGroup.get();

        Optional<Optimisation> optional = campaignGroupDB.getOptimisations()
                .stream()
                .filter(opt -> opt.getOptimisationType().equalsIgnoreCase(optimisation_type))
                .findFirst();

        if (!optional.isPresent()) {
            throw new ApplyOptimisationFailedException(
                    "optimisation-not-found-with-type (" + optimisation_type + ")");
        }

        Optimisation optimisation = optional.get();
        if (optimisation.getStatus().equalsIgnoreCase("APPLIED")) {
            throw new ApplyOptimisationFailedException(
                    "optimisation-type (" + optimisation_type + ") already-applied");
        }

        if (optimisation.getRecommendations() == null
                || optimisation.getRecommendations().size() <= 0) {
            throw new ApplyOptimisationFailedException("recommendation-not-generated, " +
                    "please-generated-before-applying");
        }

        List<Campaign> campaignList = optimisation.getRecommendations()
                .stream()
                .map(recommendation -> {
                    recommendation.getCampaign().setBudget(recommendation.getRecommendedBudget());
                    return recommendation.getCampaign();
                }).collect(Collectors.toList());


        campaignGroupDB.getCampaigns().clear();
        campaignGroupDB.getCampaigns().addAll(campaignList);
        RepositoryBeanFactory.getCampaignGroupRepository().save(campaignGroupDB);

        optimisation.setStatus("APPLIED");
        RepositoryBeanFactory.getOptimisationRepository().save(optimisation);

        return optimisation;
    }

}
