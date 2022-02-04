package com.optily.assignment.boot;

import com.optily.assignment.entity.repository.CampaignGroupRepository;
import com.optily.assignment.entity.repository.CampaignRepository;
import com.optily.assignment.entity.repository.OptimisationRepository;
import com.optily.assignment.entity.repository.RecommendationRepository;

/**
 *
 */
public class BeanFactory {
    private static CampaignGroupRepository campaignGroupRepository;
    private static CampaignRepository campaignRepository;
    private static OptimisationRepository optimisationRepository;
    private static RecommendationRepository recommendationRepository;

    private BeanFactory() {
    }

    /**
     * @return
     */
    public static CampaignRepository getCampaignRepository() {
        return campaignRepository;
    }

    protected static void setCampaignRepository(CampaignRepository campaignRepository) {
        BeanFactory.campaignRepository = campaignRepository;
    }

    /**
     * @return
     */
    public static CampaignGroupRepository getCampaignGroupRepository() {
        return campaignGroupRepository;
    }

    protected static void setCampaignGroupRepository(CampaignGroupRepository campaignGroupRepository) {
        BeanFactory.campaignGroupRepository = campaignGroupRepository;
    }

    /**
     * @return
     */
    public static OptimisationRepository getOptimisationRepository() {
        return optimisationRepository;
    }

    public static void setOptimisationRepository(OptimisationRepository optimisationRepository) {
        BeanFactory.optimisationRepository = optimisationRepository;
    }

    /**
     * @return
     */
    public static RecommendationRepository getRecommendationRepository() {
        return recommendationRepository;
    }

    public static void setRecommendationRepository(RecommendationRepository recommendationRepository) {
        BeanFactory.recommendationRepository = recommendationRepository;
    }
}
