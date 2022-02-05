package com.optily.assignment.boot;

import com.optily.assignment.entity.repository.CampaignGroupRepository;
import com.optily.assignment.entity.repository.CampaignRepository;
import com.optily.assignment.entity.repository.OptimisationRepository;
import com.optily.assignment.entity.repository.RecommendationRepository;

/**
 *
 */
public class RepositoryBeanFactory {
    private static CampaignGroupRepository campaignGroupRepository;
    private static CampaignRepository campaignRepository;
    private static OptimisationRepository optimisationRepository;
    private static RecommendationRepository recommendationRepository;

    private RepositoryBeanFactory() {
    }

    /**
     * @return
     */
    public static CampaignRepository getCampaignRepository() {
        return campaignRepository;
    }

    protected static void setCampaignRepository(CampaignRepository campaignRepository) {
        RepositoryBeanFactory.campaignRepository = campaignRepository;
    }

    /**
     * @return
     */
    public static CampaignGroupRepository getCampaignGroupRepository() {
        return campaignGroupRepository;
    }

    protected static void setCampaignGroupRepository(CampaignGroupRepository campaignGroupRepository) {
        RepositoryBeanFactory.campaignGroupRepository = campaignGroupRepository;
    }

    /**
     * @return
     */
    public static OptimisationRepository getOptimisationRepository() {
        return optimisationRepository;
    }

    protected static void setOptimisationRepository(OptimisationRepository optimisationRepository) {
        RepositoryBeanFactory.optimisationRepository = optimisationRepository;
    }

    /**
     * @return
     */
    public static RecommendationRepository getRecommendationRepository() {
        return recommendationRepository;
    }

    protected static void setRecommendationRepository(RecommendationRepository recommendationRepository) {
        RepositoryBeanFactory.recommendationRepository = recommendationRepository;
    }
}
