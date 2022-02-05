package com.optily.assignment.entity;

import javax.persistence.*;
import java.util.List;

/**
 *
 */
@Entity
@Table(name = "optimisation")
public class Optimisation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "status")
    private String status;

    @Column(name = "optimisation_type")
    private String optimisationType;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "optimisation_id")
    private List<Recommendation> recommendations;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOptimisationType() {
        return optimisationType;
    }

    public void setOptimisationType(String optimisationType) {
        this.optimisationType = optimisationType;
    }

    public List<Recommendation> getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(List<Recommendation> recommendations) {
        this.recommendations = recommendations;
    }
}
