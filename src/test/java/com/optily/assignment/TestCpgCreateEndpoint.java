package com.optily.assignment;

import com.optily.assignment.api.CampaignGroupService;
import com.optily.assignment.controller.CampaignGroupController;
import com.optily.assignment.entity.repository.CampaignGroupRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.charset.StandardCharsets;

@RunWith(SpringRunner.class)
public class TestCpgCreateEndpoint {

    @InjectMocks
    CampaignGroupController campaignGroupController;

    @Mock
    CampaignGroupRepository campaignGroupRepository;

    @Mock
    private CampaignGroupService campaignGroupService;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(campaignGroupController)
                .build();
    }

    /**
     * @throws Exception
     */
    @Test
    public void create() throws Exception {
        String fileContent = "name,budget,impressions\n" +
                "2021-July-BOF-Books,2108,36358\n" +
                "test,2108,36358\n" +
                "3_299_BBQ_G-A_CV_SHP,674,29980\n" +
                "3_299_Bulbs_G-A_CV_SHP,2000,57561\n" +
                "3_299_Containers_G-A_OT_SHP,500,25864\n" +
                "3_299_Furniture_G-A_CV_SHP,1023,68640\n" +
                "3_299_Gifts_AOC_G-A_OT_SHP,500,32743\n" +
                "3_299_Lawn_Care_G-A_CV_SHP,4600,31023\n" +
                "3_299_Vegepod_G-A_CV_SHP,1325,15209\n" +
                "3_299_Wild_Bird_G-A_AOC_SHP,500,4931\n" +
                "Optily-July2021-TOF-Test,1,0\n";

        mockMvc.perform(MockMvcRequestBuilders.multipart("/v1/campaign_group/create")
                        .file(new MockMultipartFile("file",
                                "campaigns.csv",
                                "text/plain",
                                fileContent.getBytes(StandardCharsets.UTF_8)))
                        .param("campaign_group_name", "Twitter_CPG_1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success")
                        .value("true"));
    }
}
