package com.example.jobagapi.controller;

import com.example.jobagapi.domain.model.JobCategory;
import com.example.jobagapi.domain.service.JobCategoryService;
import com.example.jobagapi.resource.JobCategoryResource;
import com.example.jobagapi.resource.SaveJobCategoryResource;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class JobCategoryController {
    @Autowired
    private JobCategoryService categoryService;
    @Autowired
    private ModelMapper mapper;

    @Operation(summary = "Update Categories", description = "Update an already existing category", tags = {"categories"})
    @PutMapping("/categories/{categoriesId}")
    public JobCategoryResource updateCategories(@PathVariable Long categoriesId, @Valid @RequestBody SaveJobCategoryResource resource) {
        JobCategory category = convertToEntity(resource);
        return convertToResource(categoryService.updateCategories(categoriesId, category));
    }

    @Operation(summary = "Get Categories", description = "Get all the categories registered in the database", tags = {"categories"})
    @GetMapping("/categories")
    public Page<JobCategoryResource> getAllCategories(Pageable pageable){
        Page<JobCategory> categoryPage = categoryService.getAllCategories(pageable);
        List<JobCategoryResource> resources = categoryPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Post Categories", description = "Create a new category", tags = {"categories"})
    @PostMapping("/categories")
    public JobCategoryResource createCategory(@Valid @RequestBody SaveJobCategoryResource resource){
        JobCategory category = convertToEntity(resource);
        return convertToResource(categoryService.createCategories(category));
    }

    @Operation(summary = "Get Categories by Id", description = "Get an specific category given an Id", tags = {"categories"})
    @GetMapping("/categories/{categoriesId}")
    public JobCategoryResource getCategoriesById(@PathVariable Long categoriesId){
        return convertToResource(categoryService.getCategoriesById(categoriesId));
    }

    @Operation(summary = "Delete Category", description = "Delete an already existing category", tags = {"categories"})
    @DeleteMapping("/categories/{categoriesId}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long categoriesId) {
        return categoryService.deleteCategories(categoriesId);
    }

    private JobCategory convertToEntity(SaveJobCategoryResource resource) { return mapper.map(resource, JobCategory.class);}

    private JobCategoryResource convertToResource(JobCategory entity){
        return mapper.map(entity, JobCategoryResource.class);
    }
}
