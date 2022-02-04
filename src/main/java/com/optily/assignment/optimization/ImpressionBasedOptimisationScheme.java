package com.optily.assignment.optimization;

import com.optily.assignment.api.OptimisationScheme;
import com.optily.assignment.api.SchemeInput;
import com.optily.assignment.api.SchemeOutput;
import com.optily.assignment.entity.Campaign;
import com.optily.assignment.entity.Recommendation;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

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

        List<Recommendation> recommendations = campaignList.stream()
                .map(campaign -> {
                    BigDecimal tempBudget =
                            sumOfBudget.multiply(BigDecimal.valueOf((float) campaign.getImpressions() / sumOfImpressions));
                    BigDecimal recommendedBudget = tempBudget.setScale(3, BigDecimal.ROUND_HALF_EVEN);

                    Recommendation recommendation = new Recommendation();
                    recommendation.setRecommendedBudget(recommendedBudget);
                    recommendation.setCampaign(campaign);
                    return recommendation;

                }).collect(Collectors.toList());

        ImpressionSchemeOutput impressionSchemeOutput = new ImpressionSchemeOutput();
        impressionSchemeOutput.setRecommendations(recommendations);

        return impressionSchemeOutput;
    }
}
