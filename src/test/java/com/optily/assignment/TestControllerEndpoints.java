package com.optily.assignment;

import com.optily.assignment.api.CampaignGroupService;
import com.optily.assignment.api.CampaignService;
import com.optily.assignment.api.OptimisationService;
import com.optily.assignment.api.RecommendationService;
import com.optily.assignment.controller.CampaignController;
import com.optily.assignment.controller.CampaignGroupController;
import com.optily.assignment.controller.OptimisationController;
import com.optily.assignment.controller.RecommendationController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringRunner.class)
public class TestControllerEndpoints {

    @InjectMocks
    CampaignGroupController campaignGroupController;

    @InjectMocks
    CampaignController campaignController;

    @InjectMocks
    OptimisationController optimisationController;

    @InjectMocks
    RecommendationController recommendationController;

    // @Mock
    //CampaignGroupRepository campaignGroupRepository;

    @Mock
    private CampaignGroupService campaignGroupService;

    @Mock
    private CampaignService campaignService;

    @Mock
    private OptimisationService optimisationService;

    @Mock
    private RecommendationService recommendationService;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(campaignGroupController,
                        campaignController, optimisationController, recommendationController)
                .build();
    }

    /**
     * @throws Exception
     */
    @Test
    public void findAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/campaign_group/find_all"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success")
                        .value("true"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isEmpty());
    }

    /**
     * @throws Exception
     */
    @Test
    public void findById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/campaign_group/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success")
                        .value("true"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data")
                        .isEmpty());
    }

    /**
     * @throws Exception
     */
    @Test
    public void findCampaignAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/campaign/find_all"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success")
                        .value("true"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isEmpty());
    }

    /**
     * @throws Exception
     */
    @Test
    public void findCampaignById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/campaign/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success")
                        .value("true"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data")
                        .isEmpty());
    }

    /**
     * @throws Exception
     */
    @Test
    public void findCampaignByCpgId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/campaign/campaign_group/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success")
                        .value("true"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data")
                        .isEmpty());
    }


    /**
     * @throws Exception
     */
    @Test
    public void findOptimisationById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/optimisation/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success")
                        .value("true"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data")
                        .isEmpty());
    }

    /**
     * @throws Exception
     */
    @Test
    public void findOptimisationByCpgId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/optimisation/campaign_group/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success")
                        .value("true"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data")
                        .isEmpty());
    }

    /**
     * @throws Exception
     */
    @Test
    public void findRecommendationByCpgId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/recommendation/campaign_group/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success")
                        .value("true"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data")
                        .isEmpty());
    }

}
