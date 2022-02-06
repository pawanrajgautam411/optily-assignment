package com.optily.assignment;

import com.optily.assignment.boot.RepositoryBeanFactory;
import com.optily.assignment.entity.CampaignGroup;
import com.optily.assignment.entity.Optimisation;
import com.optily.assignment.entity.repository.CampaignGroupRepository;
import com.optily.assignment.exception.AlreadyExistException;
import com.optily.assignment.service.RecommendationServiceImpl;
import com.optily.assignment.vo.RecommendCampaignVo;
import com.optily.assignment.vo.RecommendationResponseVo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
public class TestRecommendationGenerate {

    @InjectMocks
    private RecommendationServiceImpl recommendationService;

    @BeforeAll
    public static void beforeAll() {
        Mockito.mockStatic(RepositoryBeanFactory.class, Mockito.RETURNS_DEEP_STUBS);

        CampaignGroupRepository mockedRepo = Mockito.mock(CampaignGroupRepository.class, Mockito.RETURNS_DEEP_STUBS);
        Mockito.when(RepositoryBeanFactory.getCampaignGroupRepository())
                .thenReturn(mockedRepo);
    }

    @Test
    public void testValidation() {
        try {
            recommendationService.generate(0);
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("invalid-campaign-group-id", e.getMessage());
        }

        try {
            Mockito.when(RepositoryBeanFactory.getCampaignGroupRepository().findById(1L))
                    .thenReturn(Optional.ofNullable(null));

            recommendationService.generate(1);
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("campaign-group-not-found-with-id (1)", e.getMessage());
        }

        try {
            CampaignGroup mockGroup = Mockito.mock(CampaignGroup.class);
            Optimisation optimisation = Mockito.mock(Optimisation.class);

            Mockito.when(mockGroup.getOptimisations())
                    .thenReturn(Arrays.asList(optimisation));

            Mockito.when(RepositoryBeanFactory.getCampaignGroupRepository().findById(1L))
                    .thenReturn(Optional.ofNullable(mockGroup));

            recommendationService.generate(1);
        } catch (AlreadyExistException e) {
            Assert.assertEquals("recommendations-already-generated", e.getMessage());
        }
    }

    @Test
    public void testGenerate() {
        CampaignGroup mockGroup = Mockito.mock(CampaignGroup.class);

        Mockito.when(mockGroup.getName())
                .thenReturn("TwitterCpg");

        Mockito.when(mockGroup.getId())
                .thenReturn(23L);

        Mockito.when(RepositoryBeanFactory.getCampaignGroupRepository().findById(1L))
                .thenReturn(Optional.ofNullable(mockGroup));

        try {
            RecommendationResponseVo responseVo = recommendationService.generate(1);

            Assert.assertEquals("TwitterCpg", responseVo.getCampaign_group_name());
            Assert.assertEquals(23L, responseVo.getCampaign_group_id());

            List<RecommendCampaignVo> recommendations = responseVo.getRecommendations();

            Assert.assertNotNull(recommendations);

            for (RecommendCampaignVo recommendCampaignVo : recommendations) {
                Assert.assertNotNull(recommendCampaignVo.getOptimisation_type());
            }

        } catch (IllegalArgumentException e) {
            Assert.assertEquals("campaign-group-not-found-with-id (1)", e.getMessage());
        }
    }

}
