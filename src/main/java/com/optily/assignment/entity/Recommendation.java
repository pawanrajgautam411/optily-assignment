package com.optily.assignment.entity;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 *
 */
@Entity
@Table(name = "recommendation")
public class Recommendation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "optimisation_id")
    private Optimisation optimisation;

    @ManyToOne
    @JoinColumn(name = "campaign_id")
    private Campaign campaign;

    @Column(name = "recommendedBudget")
    private BigDecimal recommendedBudget;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Optimisation getOptimisation() {
        return optimisation;
    }

    public void setOptimisation(Optimisation optimisation) {
        this.optimisation = optimisation;
    }

    public Campaign getCampaign() {
        return campaign;
    }

    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }

    public BigDecimal getRecommendedBudget() {
        return recommendedBudget;
    }

    public void setRecommendedBudget(BigDecimal recommendedBudget) {
        this.recommendedBudget = recommendedBudget;
    }
}
