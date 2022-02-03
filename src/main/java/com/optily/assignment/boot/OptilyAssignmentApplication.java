package com.optily.assignment.boot;

import com.optily.assignment.entity.repository.CampaignGroupRepository;
import com.optily.assignment.entity.repository.CampaignRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 *
 */
@ComponentScan({"com.optily.assignment.boot",
        "com.optily.assignment.controller",
        "com.optily.assignment.entity",
        "com.optily.assignment.entity.repository"})
@SpringBootApplication
public class OptilyAssignmentApplication {

    /**
     * @param args
     */
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication
                .run(OptilyAssignmentApplication.class, args);

        BeanFactory.setCampaignRepository(applicationContext.getBean(CampaignRepository.class));
        BeanFactory.setCampaignGroupRepository(applicationContext.getBean(CampaignGroupRepository.class));
    }

}
