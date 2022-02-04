package com.optily.assignment.service;

import com.optily.assignment.api.*;
import com.optily.assignment.boot.BeanFactory;
import com.optily.assignment.entity.CampaignGroup;
import com.optily.assignment.entity.Recommendation;
import com.optily.assignment.optimization.ClickBasedOptimisationScheme;
import com.optily.assignment.optimization.ConversionBasedOptimisationScheme;
import com.optily.assignment.optimization.ImpressionBasedOptimisationScheme;
import com.optily.assignment.optimization.OptimisationType;
import com.optily.assignment.vo.RecommendationVo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 *
 */
public class RecommendationServiceImpl implements RecommendationService {
    private static final Map<OptimisationType, Class<? extends OptimisationScheme>> SCHEME_MAP;

    static {
        SCHEME_MAP = new HashMap<>();
        SCHEME_MAP.put(OptimisationType.Impression_Based_Optimisation, ImpressionBasedOptimisationScheme.class);
        SCHEME_MAP.put(OptimisationType.Click_Based_Optimisation, ClickBasedOptimisationScheme.class);
        SCHEME_MAP.put(OptimisationType.Conversion_Based_Optimisation, ConversionBasedOptimisationScheme.class);
    }

    /**
     * @param campaignGroupId
     * @return
     */
    @Override
    public RecommendationVo findByCampaignGroupId(long campaignGroupId) {
        Optional<CampaignGroup> optionalCampaignGroup = BeanFactory.getCampaignGroupRepository()
                .findById(campaignGroupId);

        if (!optionalCampaignGroup.isPresent()) {
            throw new IllegalArgumentException("campaign-group-not-found-with-id ("
                    + campaignGroupId + ")");
        }

        CampaignGroup campaignGroup = optionalCampaignGroup.get();

        RecommendationVo recommendationVo = new RecommendationVo();
        recommendationVo.setCampaign_group_id(campaignGroup.getId());
        recommendationVo.setCampaign_group_name(campaignGroup.getName());

        DefaultSchemeInputImpl schemeInput = SchemeInput.defaultSchemeInput();
        schemeInput.setCampaigns(campaignGroup.getCampaigns());
        schemeInput.setCampaignGroup(campaignGroup);

        SCHEME_MAP.forEach((key, value) -> {
            try {
                OptimisationScheme optimisationScheme = value.newInstance();
                SchemeOutput schemeOutput = optimisationScheme.recommendNow(schemeInput);

                List<Recommendation> listOfRecommendations = schemeOutput.getRecommendations();

                recommendationVo.setOptimisation_type(key.name());
                recommendationVo.setRecommendations(listOfRecommendations);

            } catch (Throwable e) {
                e.printStackTrace();
            }
        });

        return recommendationVo;
    }

}
