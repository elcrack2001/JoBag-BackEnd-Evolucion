package com.example.jobagapi.domain.repository;

import com.example.jobagapi.domain.model.JobCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobCategoryRepository extends JpaRepository<JobCategory, Long> {
    public Page<JobCategory> findById(Long Id, Pageable page);
}
