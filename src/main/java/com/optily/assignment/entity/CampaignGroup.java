package com.optily.assignment.entity;

import javax.persistence.*;
import java.util.List;

/**
 *
 */
@Entity
@Table(name = "campaign_group")
public class CampaignGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "campaign_group_id")
    private List<Campaign> campaigns;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "campaign_group_id")
    private List<Optimisation> optimisations;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Campaign> getCampaigns() {
        return campaigns;
    }

    public void setCampaigns(List<Campaign> campaigns) {
        this.campaigns = campaigns;
    }

    public List<Optimisation> getOptimisations() {
        return optimisations;
    }

    public void setOptimisations(List<Optimisation> optimisations) {
        this.optimisations = optimisations;
    }
}
