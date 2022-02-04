package com.optily.assignment.optimization;

import com.optily.assignment.api.SchemeOutput;
import com.optily.assignment.entity.Recommendation;

import java.util.List;

/**
 *
 */
public class ImpressionSchemeOutput implements SchemeOutput {
    private List<Recommendation> recommendations;

    /**
     * @return
     */
    @Override
    public List<Recommendation> getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(List<Recommendation> recommendations) {
        this.recommendations = recommendations;
    }

}
