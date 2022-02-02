package com.optily.assignment.boot;

import com.optily.assignment.api.CampaignGroupService;
import com.optily.assignment.service.CampaignGroupServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 */
@Configuration
public class OptilyConfig {

    /**
     * @return
     */
    @Bean
    public CampaignGroupService getCampaignService() {
        return new CampaignGroupServiceImpl();
    }
}
