package com.example.jobagapi.resource;

import javax.validation.constraints.Size;

public class SaveJobCategoryResource {
    @Size(max = 30)
    private String name;

    public String getName() { return name; }

    public SaveJobCategoryResource setName(String name) {
        this.name = name;
        return this;
    }
}
