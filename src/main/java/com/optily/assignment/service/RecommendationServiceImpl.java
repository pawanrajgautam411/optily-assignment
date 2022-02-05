package com.optily.assignment.service;

import com.optily.assignment.api.*;
import com.optily.assignment.boot.RepositoryBeanFactory;
import com.optily.assignment.entity.CampaignGroup;
import com.optily.assignment.entity.Optimisation;
import com.optily.assignment.optimization.ImpressionBasedOptimisationScheme;
import com.optily.assignment.optimization.OptimisationType;
import com.optily.assignment.vo.RecommendCampaignVo;
import com.optily.assignment.vo.RecommendationResponseVo;

import java.util.*;
import java.util.stream.Collectors;

/**
 *
 */
public class RecommendationServiceImpl implements RecommendationService {
    public static final Map<OptimisationType, Class<? extends OptimisationScheme>> SCHEME_MAP;

    static {
        SCHEME_MAP = new HashMap<>();
        SCHEME_MAP.put(OptimisationType.Impression_Based_Optimisation, ImpressionBasedOptimisationScheme.class);
//        SCHEME_MAP.put(OptimisationType.Click_Based_Optimisation, ClickBasedOptimisationScheme.class);
//        SCHEME_MAP.put(OptimisationType.Conversion_Based_Optimisation, ConversionBasedOptimisationScheme.class);
    }

    /**
     * @param campaignGroupId
     * @return
     */
    @Override
    public RecommendationResponseVo generate(long campaignGroupId) {
        return new ActionGenerateRecommendation().generateNow(campaignGroupId);
    }


    /**
     * @param campaignGroupId
     * @param optimisation_type
     * @return
     */
    @Override
    public Optimisation applyOptimisation(long campaignGroupId,
                                          String optimisation_type) {

        return new ActionApplyOptimisation().applyNow(campaignGroupId, optimisation_type);
    }

    /**
     * @param campaignGroupId
     * @return
     */
    @Override
    public RecommendationResponseVo findByCampaignGroupId(long campaignGroupId) {
        Optional<CampaignGroup> optionalCampaignGroup = RepositoryBeanFactory.getCampaignGroupRepository()
                .findById(campaignGroupId);

        if (!optionalCampaignGroup.isPresent()) {
            throw new IllegalArgumentException("campaign-group-not-found-with-id ("
                    + campaignGroupId + ")");
        }

        CampaignGroup campaignGroupDB = optionalCampaignGroup.get();

        if (campaignGroupDB.getOptimisations() == null
                || campaignGroupDB.getOptimisations().size() <= 0) {
            return new ActionGenerateRecommendation().generateNow(campaignGroupId);
        }

        List<Optimisation> optimisations = campaignGroupDB.getOptimisations()
                .stream()
                .filter(optimisation -> optimisation.getStatus().equalsIgnoreCase(
                        "NOT_APPLIED"))
                .collect(Collectors.toList());


        if (optimisations.size() <= 0) {
            return null;
        }

        RecommendationResponseVo recommendationResponseVo = new RecommendationResponseVo();
        recommendationResponseVo.setCampaign_group_id(campaignGroupDB.getId());
        recommendationResponseVo.setCampaign_group_name(campaignGroupDB.getName());
        recommendationResponseVo.setRecommendations(new ArrayList<>());

        optimisations.forEach(optimisation -> {
            try {
                DefaultSchemeInputImpl schemeInput = SchemeInput.defaultSchemeInput();
                schemeInput.setCampaigns(campaignGroupDB.getCampaigns());
                schemeInput.setCampaignGroup(campaignGroupDB);

                Class<? extends OptimisationScheme> optimisationSchemeClass = SCHEME_MAP
                        .get(OptimisationType.valueOf(optimisation.getOptimisationType()));

                OptimisationScheme optimisationScheme = optimisationSchemeClass.newInstance();
                SchemeOutput schemeOutput = optimisationScheme.recommendNow(schemeInput);

                RecommendCampaignVo recommendCampaignVo = schemeOutput.getRecommendations();
                recommendationResponseVo.getRecommendations().add(recommendCampaignVo);

            } catch (Throwable e) {
                e.printStackTrace();
            }
        });

        return recommendationResponseVo;
    }
}
