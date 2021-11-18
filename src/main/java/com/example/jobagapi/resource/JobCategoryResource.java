package com.example.jobagapi.resource;

public class JobCategoryResource {
    private Long id;
    private String name;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id;}

    public String getName() { return name; }

    public JobCategoryResource setName(String name) {
        this.name = name;
        return this;
    }
}
