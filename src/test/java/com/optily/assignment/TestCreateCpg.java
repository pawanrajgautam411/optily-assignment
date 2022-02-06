package com.optily.assignment;

import com.optily.assignment.boot.RepositoryBeanFactory;
import com.optily.assignment.entity.CampaignGroup;
import com.optily.assignment.entity.repository.CampaignGroupRepository;
import com.optily.assignment.exception.AlreadyExistException;
import com.optily.assignment.service.CampaignGroupServiceImpl;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.Arrays;

@RunWith(SpringRunner.class)
public class TestCreateCpg {

    @InjectMocks
    private CampaignGroupServiceImpl campaignGroupService;

    @BeforeClass
    public static void beforeAll() {
        Mockito.mockStatic(RepositoryBeanFactory.class, Mockito.RETURNS_DEEP_STUBS);

        CampaignGroupRepository mockedRepo = Mockito.mock(CampaignGroupRepository.class, Mockito.RETURNS_DEEP_STUBS);
        Mockito.when(RepositoryBeanFactory.getCampaignGroupRepository())
                .thenReturn(mockedRepo);
    }

    @Test
    public void testValidation() {
        CampaignGroup group = Mockito.mock(CampaignGroup.class);
        Mockito.when(RepositoryBeanFactory.getCampaignGroupRepository()
                        .findByName("TwitterCPG_1"))
                .thenReturn(Arrays.asList(group));

        try {
            File file = new File("campaigns.csv");

            CampaignGroup campaignGroup = campaignGroupService
                    .createNow(file, "TwitterCPG_1");

        } catch (AlreadyExistException e) {
            Assert.assertEquals("campaign-group-name (TwitterCPG_1) already-used",
                    e.getMessage());
        }
    }


    @Test
    public void testCpgCreation() {
        Mockito.when(RepositoryBeanFactory.getCampaignGroupRepository()
                        .findByName("TwitterCPG_1"))
                .thenReturn(null);

        File file = new File("campaigns.csv");

        CampaignGroup campaignGroup = campaignGroupService
                .createNow(file, "TwitterCPG_1");

        Assert.assertNotNull(campaignGroup);
        Assert.assertEquals("TwitterCPG_1", campaignGroup.getName());
        Assert.assertNotNull(campaignGroup.getCampaigns());

    }

}
