package com.optily.assignment;

import com.optily.assignment.boot.RepositoryBeanFactory;
import com.optily.assignment.entity.Campaign;
import com.optily.assignment.entity.CampaignGroup;
import com.optily.assignment.entity.Optimisation;
import com.optily.assignment.entity.Recommendation;
import com.optily.assignment.entity.repository.CampaignGroupRepository;
import com.optily.assignment.exception.ApplyOptimisationFailedException;
import com.optily.assignment.service.RecommendationServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;

@RunWith(SpringRunner.class)
public class TestRecommendationErrors {

    @InjectMocks
    private RecommendationServiceImpl recommendationService;

    @BeforeAll
    public static void before() {
        Mockito.mockStatic(RepositoryBeanFactory.class, Mockito.RETURNS_DEEP_STUBS);

        Mockito.when(RepositoryBeanFactory.getCampaignGroupRepository())
                .thenReturn(Mockito.mock(CampaignGroupRepository.class));
    }

    @Test
    public void testInvalidParams() {

        try {
            recommendationService.applyOptimisation(-1, "Impression_Based_Optimisation");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("invalid-campaign-group-id", e.getMessage());
        }

        try {
            recommendationService.applyOptimisation(1, "");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("invalid-optimisation-type", e.getMessage());
        }

        try {
            recommendationService.applyOptimisation(1, "Click_Based_Optimisation");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("optimisation-type (Click_Based_Optimisation) is-only-available-for-DEMO", e.getMessage());
        }

        try {
            recommendationService.applyOptimisation(1, "some_random_value");
        } catch (ApplyOptimisationFailedException e) {
            Assert.assertEquals("optimisation-not-found-with-type (some_random_value)", e.getMessage());
        }

        try {
            recommendationService.applyOptimisation(1, "Impression_Based_Optimisation");
        } catch (ApplyOptimisationFailedException e) {
            Assert.assertEquals("optimisation-type (Impression_Based_Optimisation) already-applied", e.getMessage());
        }

    }

    @Test
    public void testCpgNotFound() {
        try {
            recommendationService.applyOptimisation(1, "Impression_Based_Optimisation");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("campaign-group-not-found-with-id (1)", e.getMessage());
        }
    }

    @Test
    public void testRecommendations() {
        CampaignGroup mockCpg = Mockito.mock(CampaignGroup.class);

        Optimisation optimisation = Mockito.mock(Optimisation.class);

        Mockito.when(mockCpg.getOptimisations())
                .thenReturn(Arrays.asList(optimisation));

        Mockito.when(optimisation.getStatus())
                .thenReturn("NOT_APPLIED");

        Mockito.when(optimisation.getOptimisationType())
                .thenReturn("Impression_Based_Optimisation");

        Recommendation recommendation = Mockito.mock(Recommendation.class);
        recommendation.setRecommendedBudget(BigDecimal.valueOf(5021.341));
        Mockito.when(optimisation.getRecommendations())
                .thenReturn(Arrays.asList(recommendation));

        Campaign campaign = Mockito.mock(Campaign.class);

        Mockito.when(recommendation.getCampaign())
                .thenReturn(campaign);


        Mockito.when(RepositoryBeanFactory.getCampaignGroupRepository().findById(1L))
                .thenReturn(Optional.of(mockCpg));

        Assert.assertNotNull(recommendationService.applyOptimisation(1,
                "Impression_Based_Optimisation"));

    }

}
