package com.optily.assignment.service;

import com.optily.assignment.api.DefaultSchemeInputImpl;
import com.optily.assignment.api.OptimisationScheme;
import com.optily.assignment.api.SchemeInput;
import com.optily.assignment.api.SchemeOutput;
import com.optily.assignment.boot.RepositoryBeanFactory;
import com.optily.assignment.entity.CampaignGroup;
import com.optily.assignment.exception.ApplyOptimisationFailedException;
import com.optily.assignment.optimization.OptimisationType;
import com.optily.assignment.vo.RecommendationResponseVo;

import java.util.Optional;

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
    public RecommendationResponseVo applyNow(long campaignGroupId,
                                             String optimisation_type) {

        Optional<CampaignGroup> optionalCampaignGroup = RepositoryBeanFactory.getCampaignGroupRepository()
                .findById(campaignGroupId);

        if (!optionalCampaignGroup.isPresent()) {
            throw new IllegalArgumentException("campaign-group-not-found-with-id ("
                    + campaignGroupId + ")");
        }

        CampaignGroup campaignGroupDB = optionalCampaignGroup.get();

        OptimisationType optimisationType = null;
        try {
            optimisationType = OptimisationType.valueOf(optimisation_type);
            if (optimisationType == null) {
                throw new IllegalArgumentException("optimisation-type ("
                        + optimisation_type + ") not-found");
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("optimisation-type ("
                    + optimisation_type + ") not-found", e);
        }

        if (campaignGroupDB.getOptimisations() != null) {
            boolean isApplied = campaignGroupDB.getOptimisations()
                    .stream()
                    .anyMatch(optimisation ->
                            optimisation.getOptimisationType().equalsIgnoreCase(optimisation_type)
                                    && optimisation.getStatus().equalsIgnoreCase("APPLIED"));
            if (isApplied) {
                throw new ApplyOptimisationFailedException("optimisation-type ("
                        + optimisation_type + ") already-applied");
            }
        }

        if (optimisationType == OptimisationType.Click_Based_Optimisation
                || optimisationType == OptimisationType.Conversion_Based_Optimisation) {
            throw new IllegalArgumentException("optimisation-type ("
                    + optimisationType + ") is-only-available-for-DEMO");
        }

        Class<? extends OptimisationScheme> schemeClass = RecommendationServiceImpl.SCHEME_MAP
                .get(optimisationType);

        if (schemeClass == null) {
            throw new IllegalArgumentException("optimisation-type ("
                    + optimisationType + ") not-found");
        }

        try {
            RecommendationResponseVo recommendationResponseVo = new RecommendationResponseVo();
            recommendationResponseVo.setCampaign_group_id(campaignGroupDB.getId());
            recommendationResponseVo.setCampaign_group_name(campaignGroupDB.getName());

            DefaultSchemeInputImpl schemeInput = SchemeInput.defaultSchemeInput();
            schemeInput.setCampaigns(campaignGroupDB.getCampaigns());
            schemeInput.setCampaignGroup(campaignGroupDB);

            OptimisationScheme optimisationScheme = schemeClass.newInstance();
            SchemeOutput schemeOutput = optimisationScheme.recommendNow(schemeInput);

//            List<Recommendation> listOfRecommendations = schemeOutput.getRecommendations();
//
//            recommendationResponseVo.setOptimisation_type(optimisationType.name());
//            recommendationResponseVo.setRecommendations(listOfRecommendations);
//
//            insertIntoDB(recommendationResponseVo, campaignGroupDB);

            return recommendationResponseVo;

        } catch (Throwable e) {
            e.printStackTrace();
            throw new ApplyOptimisationFailedException("optimisation-could-not-be-applied, cause:" + e.getMessage(),
                    e);
        }
    }

    /**
     * @param recommendationResponseVo
     * @param campaignGroupDB
     */
    private void insertIntoDB(RecommendationResponseVo recommendationResponseVo,
                              CampaignGroup campaignGroupDB) {

//        Optimisation optimisation = new Optimisation();
//        optimisation.setStatus("APPLIED");
//        optimisation.setOptimisationType(recommendationResponseVo.getOptimisation_type());
//
//        campaignGroupDB.getOptimisations().clear();
//        campaignGroupDB.getOptimisations().add(optimisation);
//
//        List<Campaign> finalListOfCampaigns = recommendationResponseVo.getRecommendations()
//                .stream()
//                .map(recommendation -> {
//                    recommendation.getCampaign()
//                            .setBudget(recommendation.getRecommendedBudget());
//                    return recommendation.getCampaign();
//                }).
//                collect(Collectors.toList());
//
//        campaignGroupDB.getCampaigns().clear();
//        campaignGroupDB.getCampaigns().addAll(finalListOfCampaigns);
//        RepositoryBeanFactory.getCampaignGroupRepository().save(campaignGroupDB);
    }
}
