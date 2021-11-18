package com.example.jobagapi.domain.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "jobCategories")
public class JobCategory extends AuditModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 30)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY,
    cascade = {CascadeType.PERSIST, CascadeType.MERGE},
    mappedBy = "jobCategories")
    private List<JobOffer> jobOffers;

    public Long getId() { return id; }

    public JobCategory setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() { return name;}

    public JobCategory setName(String name) {
        this.name = name;
        return this;
    }

    public List<JobOffer> getJobOffers() { return jobOffers; }

    public void setJobOffers(List<JobOffer> jobOffers) {
        this.jobOffers = jobOffers;
    }
}
