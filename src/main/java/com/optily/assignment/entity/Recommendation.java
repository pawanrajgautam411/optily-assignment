package com.optily.assignment.entity;

import java.math.BigDecimal;

/**
 *
 */
public class Recommendation {
    private long id;
    private Optimisation optimisation;
    private Campaign campaign;
    private BigDecimal budget;

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

    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }
}
