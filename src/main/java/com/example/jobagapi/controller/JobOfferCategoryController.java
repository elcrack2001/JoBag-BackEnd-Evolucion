package com.example.jobagapi.controller;

import com.example.jobagapi.domain.model.JobOffer;
import com.example.jobagapi.domain.service.JobOfferService;
import com.example.jobagapi.resource.JobOfferResource;
import com.example.jobagapi.resource.SaveJobOfferResource;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class JobOfferCategoryController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private JobOfferService jobOfferService;

    @Operation(summary = "Assign Category to Job Offers",
    description = "Establishes association between categories and Job Offers",
    tags = {"jobOffers"})
    @PostMapping("/jobOffers/{jobOfferId}/categories/{categoriesId}")
    public JobOfferResource assignJobOfferCategory(
            @PathVariable Long jobOfferId,
            @PathVariable Long categoriesId) {
        return convertToResource(jobOfferService.assignJobOfferCategory(jobOfferId, categoriesId));
    }

    @Operation(summary = "Remove assignment between Job Offer and Categories",
    description = "Ends association between Job Offers and Categories",
    tags = {"jobOffers"})
    @DeleteMapping("/jobOffers/{jobOfferId}/categories.{categoriesId}")
    public JobOfferResource unassignJobOfferCategory(
            @PathVariable Long jobOfferId,
            @PathVariable Long categoriesId) {
        return convertToResource(jobOfferService.unassignJobOfferCategory(jobOfferId, categoriesId));
    }

    @Operation(summary = "List assigment between Categories and Job Offers",
    description = "List association between Categories and Job Offers",
    tags = {"jobOffers"})
    @GetMapping("/categories/{categoriesId}/jobOffers")
    public Page<JobOfferResource> getAllJobOfferByCategoriesId(
            @PathVariable Long categoriesId,
            Pageable pageable) {
        Page<JobOffer> postsPage = jobOfferService.getAllJobOfferCategories(categoriesId, pageable);
        List<JobOfferResource> resources = postsPage.getContent().stream()
                .map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    private JobOffer convertToEntity(SaveJobOfferResource resource) {
        return mapper.map(resource, JobOffer.class);
    }

    private JobOfferResource convertToResource(JobOffer entity) {
        return mapper.map(entity, JobOfferResource.class);
    }
}
