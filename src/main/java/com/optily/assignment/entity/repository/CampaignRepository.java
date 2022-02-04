package com.optily.assignment.entity.repository;

import com.optily.assignment.entity.Campaign;
import com.optily.assignment.entity.CampaignGroup;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 */
@Repository
public interface CampaignRepository extends CrudRepository<Campaign, Long> {

}
