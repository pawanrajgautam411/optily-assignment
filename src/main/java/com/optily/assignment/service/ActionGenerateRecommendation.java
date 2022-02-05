package com.optily.assignment.service;

import com.optily.assignment.api.DefaultSchemeInputImpl;
import com.optily.assignment.api.OptimisationScheme;
import com.optily.assignment.api.SchemeInput;
import com.optily.assignment.api.SchemeOutput;
import com.optily.assignment.boot.RepositoryBeanFactory;
import com.optily.assignment.entity.CampaignGroup;
import com.optily.assignment.entity.Optimisation;
import com.optily.assignment.entity.Recommendation;
import com.optily.assignment.exception.AlreadyExistException;
import com.optily.assignment.vo.RecommendCampaignVo;
import com.optily.assignment.vo.RecommendationResponseVo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 */
class ActionGenerateRecommendation {
    /**
     *
     */
    protected ActionGenerateRecommendation() {
    }

    /**
     * @param campaignGroupId
     * @return
     */
    protected RecommendationResponseVo generateNow(long campaignGroupId) {
        Optional<CampaignGroup> optionalCampaignGroup = RepositoryBeanFactory.getCampaignGroupRepository()
                .findById(campaignGroupId);

        if (!optionalCampaignGroup.isPresent()) {
            throw new IllegalArgumentException("campaign-group-not-found-with-id ("
                    + campaignGroupId + ")");
        }

        CampaignGroup campaignGroupDB = optionalCampaignGroup.get();

        if (campaignGroupDB.getOptimisations() != null
                && campaignGroupDB.getOptimisations().size() > 0) {
            throw new AlreadyExistException("recommendations-already-generated");
        }

        RecommendationResponseVo recommendationResponseVo = new RecommendationResponseVo();
        recommendationResponseVo.setCampaign_group_id(campaignGroupDB.getId());
        recommendationResponseVo.setCampaign_group_name(campaignGroupDB.getName());
        recommendationResponseVo.setRecommendations(new ArrayList<>());

        RecommendationServiceImpl.SCHEME_MAP.forEach((key, value) -> {
            try {
                Optimisation optimisation = new Optimisation();
                optimisation.setStatus("NOT_APPLIED");
                optimisation.setOptimisationType(key.name());
                optimisation.setRecommendations(new ArrayList<>());

                DefaultSchemeInputImpl schemeInput = SchemeInput.defaultSchemeInput();
                schemeInput.setCampaigns(campaignGroupDB.getCampaigns());
                schemeInput.setCampaignGroup(campaignGroupDB);

                OptimisationScheme optimisationScheme = value.newInstance();
                SchemeOutput schemeOutput = optimisationScheme.recommendNow(schemeInput);

                RecommendCampaignVo recommendCampaignVo = schemeOutput.getRecommendations();
                List<Recommendation> campaigns = recommendCampaignVo.getCampaigns();

                optimisation.getRecommendations().addAll(campaigns);

                campaignGroupDB.getOptimisations().add(optimisation);
                recommendationResponseVo.getRecommendations().add(recommendCampaignVo);

            } catch (Throwable e) {
                e.printStackTrace();
            }
        });

        RepositoryBeanFactory.getCampaignGroupRepository().save(campaignGroupDB);

        return recommendationResponseVo;
    }

}
