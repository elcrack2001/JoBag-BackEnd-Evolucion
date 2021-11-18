package com.example.jobagapi.domain.service;

import com.example.jobagapi.domain.model.JobCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface JobCategoryService {
    Page<JobCategory> getAllCategories(Pageable pageable);
    JobCategory getCategoriesById(Long categoriesId);
    JobCategory createCategories(JobCategory categories);
    JobCategory updateCategories(Long categoriesId, JobCategory categoriesRequest);
    ResponseEntity<?> deleteCategories(Long categoriesId);
}
