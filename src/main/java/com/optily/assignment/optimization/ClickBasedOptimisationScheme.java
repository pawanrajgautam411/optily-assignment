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
public class ClickBasedOptimisationScheme implements OptimisationScheme {

    /**
     * @param schemeInput
     * @return
     */
    @Override
    public SchemeOutput recommendNow(SchemeInput schemeInput) {
        List<Campaign> campaignList = schemeInput.getCampaigns();

        RecommendCampaignVo recommendCampaignVo = new RecommendCampaignVo();
        recommendCampaignVo.setOptimisation_type(OptimisationType.Click_Based_Optimisation.name());
        recommendCampaignVo.setCampaigns(new ArrayList<>());


        campaignList
                .stream()
                .forEach(campaign -> {
                    Recommendation recommendation = new Recommendation();
                    recommendation.setRecommendedBudget(campaign.getBudget().add(BigDecimal.TEN));
                    recommendation.setCampaign(campaign);
                    recommendCampaignVo.getCampaigns().add(recommendation);

                });

        ImpressionSchemeOutput impressionSchemeOutput = new ImpressionSchemeOutput();
        impressionSchemeOutput.setRecommendations(recommendCampaignVo);

        return impressionSchemeOutput;
    }
}
