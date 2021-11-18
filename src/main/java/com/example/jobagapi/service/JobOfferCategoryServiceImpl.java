package com.example.jobagapi.service;

import com.example.jobagapi.domain.model.JobCategory;
import com.example.jobagapi.domain.repository.JobCategoryRepository;
import com.example.jobagapi.domain.service.JobCategoryService;
import com.example.jobagapi.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class JobOfferCategoryServiceImpl implements JobCategoryService {
    @Autowired
    private JobCategoryRepository jobCategoryRepository;

    @Override
    public Page<JobCategory> getAllCategories(Pageable pageable) {
        return jobCategoryRepository.findAll(pageable);
    }

    @Override
    public JobCategory getCategoriesById(Long categoriesId) {
        return jobCategoryRepository.findById(categoriesId)
                .orElseThrow(() -> new ResourceNotFoundException("Categories","Id",categoriesId));
    }

    @Override
    public JobCategory createCategories(JobCategory categories) {
        return jobCategoryRepository.save(categories);
    }

    @Override
    public JobCategory updateCategories(Long categoriesId, JobCategory categoriesRequest) {
        JobCategory category = jobCategoryRepository.findById(categoriesId)
                .orElseThrow(() -> new ResourceNotFoundException("Category","Id",categoriesId));
        return jobCategoryRepository.save(
                category.setName(categoriesRequest.getName())
        );
    }

    @Override
    public ResponseEntity<?> deleteCategories(Long categoriesId) {
        JobCategory category = jobCategoryRepository.findById(categoriesId)
                .orElseThrow(() -> new ResourceNotFoundException("Category","Id",categoriesId));
        jobCategoryRepository.delete(category);
        return ResponseEntity.ok().build();
    }
}
