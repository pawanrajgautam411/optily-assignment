package com.optily.assignment.optimization;

import com.optily.assignment.api.OptimisationScheme;
import com.optily.assignment.api.SchemeInput;
import com.optily.assignment.api.SchemeOutput;
import com.optily.assignment.entity.Campaign;
import com.optily.assignment.entity.Recommendation;
import com.optily.assignment.vo.RecommendCampaignVo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class ImpressionBasedOptimisationScheme implements OptimisationScheme {

    /**
     * @param schemeInput
     * @return
     */
    @Override
    public SchemeOutput recommendNow(SchemeInput schemeInput) {

        List<Campaign> campaignList = schemeInput.getCampaigns();

        final BigDecimal sumOfBudget = campaignList.stream()
                .map(campaign -> campaign.getBudget())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        final long sumOfImpressions = campaignList.stream()
                .mapToLong(campaign -> campaign.getImpressions())
                .sum();

        RecommendCampaignVo recommendCampaignVo = new RecommendCampaignVo();
        recommendCampaignVo.setOptimisation_type(OptimisationType.Impression_Based_Optimisation.name());
        recommendCampaignVo.setCampaigns(new ArrayList<>());

        campaignList
                .stream()
                .forEach(campaign -> {
                    BigDecimal tempBudget =
                            sumOfBudget.multiply(BigDecimal.valueOf((float) campaign.getImpressions() / sumOfImpressions));
                    BigDecimal recommendedBudget = tempBudget.setScale(3, BigDecimal.ROUND_HALF_EVEN);

                    Recommendation recommendation = new Recommendation();
                    recommendation.setRecommendedBudget(recommendedBudget);
                    recommendation.setCampaign(campaign);
                    recommendCampaignVo.getCampaigns().add(recommendation);

                });

        ImpressionSchemeOutput impressionSchemeOutput = new ImpressionSchemeOutput();
        impressionSchemeOutput.setRecommendations(recommendCampaignVo);

        return impressionSchemeOutput;
    }
}
