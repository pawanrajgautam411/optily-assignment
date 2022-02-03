package com.optily.assignment.boot;

import com.optily.assignment.api.CampaignGroupService;
import com.optily.assignment.api.CampaignService;
import com.optily.assignment.service.CampaignGroupServiceImpl;
import com.optily.assignment.service.CampaignServiceImpl;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;


/**
 *
 */
@Configuration
@EnableJpaRepositories("com.optily.assignment.entity")
public class OptilyConfig {

    @Autowired
    private Environment environment;

    /**
     * @return
     */
    @Bean
    public CampaignGroupService campaignGroupService() {
        return new CampaignGroupServiceImpl();
    }

    /**
     * @return
     */
    @Bean
    public CampaignService campaignService() {
        return new CampaignServiceImpl();
    }

    /**
     * @return
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        try {
            LocalContainerEntityManagerFactoryBean lfb = new LocalContainerEntityManagerFactoryBean();
            lfb.setDataSource(dataSource());
            lfb.setPersistenceProviderClass(HibernatePersistenceProvider.class);
            lfb.setPackagesToScan("com.optily.assignment.entity");

            Properties hibernateProps = new Properties();
            hibernateProps.setProperty("hibernate.dialect",
                    environment.getProperty("hibernate.dialect"));
            hibernateProps.setProperty("hibernate.show_sql",
                    environment.getProperty("hibernate.show_sql"));
            hibernateProps.setProperty("hibernate.hbm2ddl.auto",
                    environment.getProperty("hibernate.hbm2ddl.auto"));


            lfb.setJpaProperties(hibernateProps);
            return lfb;

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * @return
     */
    @Bean
    public DataSource dataSource() {
        try {
            DriverManagerDataSource ds = new DriverManagerDataSource();
            ds.setUrl(environment.getProperty("database.url"));
            ds.setUsername(environment.getProperty("database.user"));
            ds.setPassword(environment.getProperty("database.password"));
            ds.setDriverClassName(environment.getProperty("database.driver"));
            return ds;

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * @return
     */
    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }
}
