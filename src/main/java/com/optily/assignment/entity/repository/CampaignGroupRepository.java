package com.optily.assignment.entity.repository;

import com.optily.assignment.entity.CampaignGroup;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 */
@Repository
public interface CampaignGroupRepository extends CrudRepository<CampaignGroup, Long> {

    @Query("select cpg from CampaignGroup cpg where LOWER(cpg.name) = LOWER(:campaignGroupName)")
    List<CampaignGroup> findByName(String campaignGroupName);
}
