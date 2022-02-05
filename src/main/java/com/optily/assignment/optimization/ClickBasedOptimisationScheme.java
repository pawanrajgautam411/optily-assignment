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
public class ClickBasedOptimisationScheme implements OptimisationScheme {

    /**
     * @param schemeInput
     * @return
     */
    @Override
    public SchemeOutput recommendNow(SchemeInput schemeInput) {
        List<Campaign> campaignList = schemeInput.getCampaigns();

        List<Recommendation> recommendations = campaignList.stream()
                .map(campaign -> {
                    Recommendation recommendation = new Recommendation();
                    recommendation.setRecommendedBudget(campaign.getBudget().add(BigDecimal.TEN));
                    recommendation.setCampaign(campaign);
                    return recommendation;

                }).collect(Collectors.toList());

        ImpressionSchemeOutput impressionSchemeOutput = new ImpressionSchemeOutput();
        impressionSchemeOutput.setRecommendations(recommendations);

        return impressionSchemeOutput;
    }
}
