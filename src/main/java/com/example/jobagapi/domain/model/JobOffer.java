package com.example.jobagapi.domain.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "jobOffers")
public class JobOffer extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    public JobOffer() {
    }

    public JobOffer(Long id, Employeer employeer, String description, LocalDate begin_date_offer, LocalDate final_date_offer, Long salary, String type, String title, String direction) {
        this.id = id;
        this.employeer = employeer;
        this.description = description;
        this.begin_date_offer = begin_date_offer;
        this.final_date_offer = final_date_offer;
        this.salary = salary;
        this.type = type;
        this.title = title;
        this.direction = direction;
    }

    public Employeer getEmployeer() {
        return employeer;
    }

    public JobOffer setEmployeer(Employeer employeer) {
        this.employeer = employeer;
        return this;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employeer_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Employeer employeer;

    @NotNull
    @Size(max = 200)
    private String description;

    @DateTimeFormat
    private LocalDate begin_date_offer;

    @DateTimeFormat
    private LocalDate final_date_offer;

    @NotNull
    private Long salary;

    @NotNull
    private String type;

    @NotNull
    private String title;

    @NotNull
    @Size(max = 60)
    private String direction;

    @ManyToMany(fetch = FetchType.LAZY,
    cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "joboffer_category",
    joinColumns = {@JoinColumn(name = "jobOffer_id")},
    inverseJoinColumns = {@JoinColumn(name = "category_id")})
    private List<JobCategory> jobCategories;

    public Long getId() {
        return id;
    }

    public JobOffer setId(Long id) {
        this.id = id;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public JobOffer setDescription(String description) {
        this.description = description;
        return this;
    }

    public LocalDate getBegin_date_offer() {
        return begin_date_offer;
    }

    public JobOffer setBegin_date_offer(LocalDate begin_date_offer) {
        this.begin_date_offer = begin_date_offer;
        return this;
    }

    public LocalDate getFinal_date_offer() {
        return final_date_offer;
    }

    public JobOffer setFinal_date_offer(LocalDate final_date_offer) {
        this.final_date_offer = final_date_offer;
        return this;
    }

    public Long getSalary() {
        return salary;
    }

    public JobOffer setSalary(Long salary) {
        this.salary = salary;
        return this;
    }

    public String getDirection() {
        return direction;
    }

    public JobOffer setDirection(String direction) {
        this.direction = direction;
        return this;
    }

    public String getType() {
        return type;
    }

    public JobOffer setType(String type) {
        this.type = type;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public JobOffer setTitle(String title) {
        this.title = title;
        return this;
    }

    public boolean hasCategoies(JobCategory categories) { return this.getCategories().contains(categories); }

    public JobOffer addCategories(JobCategory categories) {
        if(!hasCategoies(categories)) {
            this.getCategories().add(categories);
        }
        return this;
    }

    public JobOffer removeCategories(JobCategory categories) {
        if (this.hasCategoies(categories))
            this.getCategories().remove(categories);
        return this;
    }

    public List<JobCategory> getCategories() {return jobCategories; }
}
