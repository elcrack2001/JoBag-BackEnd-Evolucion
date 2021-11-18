package com.example.jobagapi.controller;

import com.example.jobagapi.domain.model.PostulantJob;
import com.example.jobagapi.domain.service.PostulantJobService;
import com.example.jobagapi.resource.PostulantJobResource;
import com.example.jobagapi.resource.SavePostulantJobResource;
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
/**/
@RestController
@RequestMapping("/api")
public class PostulantJobController {
    @Autowired
    private PostulantJobService postulantJobService;
    @Autowired
    private ModelMapper mapper;


    @Operation(summary="Postulant Jobs", description="Create a new postulant job related to an existing postulant and job offer given an postulant Id and job offer Id",  tags={"postulant_jobs"})
    @PostMapping("/postulants/{postulantId}/joboffers/{jobofferId}/postulantjobs")
    public PostulantJobResource createJobOffer(
            @PathVariable Long postulantId,
            @PathVariable Long jobofferId,
            @Valid @RequestBody SavePostulantJobResource resource) {
        return convertToResource(postulantJobService.createPostulantJob(postulantId,jobofferId,convertToEntity(resource)));
    }

    @Operation(summary="Put Postulant Jobs", description="Update an already existing postulant job given an existing postulant Id and job offer Id",  tags={"postulant_jobs"})
    @PutMapping("/postulant/{postulantId}/joboffers/{jobofferId}/postulantjobs")
    public PostulantJobResource updatePostulantJob(
            @PathVariable Long postulantId,
            @PathVariable Long jobofferId,
            @Valid @RequestBody SavePostulantJobResource resource) {
        return convertToResource(postulantJobService.updatePostulantJob(postulantId, jobofferId,convertToEntity(resource)));
    }

    @Operation(summary="Delete postulant job by postulant ID and job offer ID", description="Delete an already existing postulant job given an existing postulant Id and job offer Id",  tags={"postulant_jobs"})
    @DeleteMapping("/postulants/{postulantId}/joboffers/{jobofferId}/postulantjobs")
    public ResponseEntity<?> deletePostulantJob(
            @PathVariable Long postulantId,
            @PathVariable Long jobofferId) {
        return postulantJobService.deletePostulantJob(postulantId, jobofferId);
    }

    @Operation(summary = "Get All Postulant Job", description = "Get all the postulant job registered in the database", tags = {"postulant_jobs"})
    @GetMapping("/postulantjobs")
    public Page<PostulantJobResource> getAllPostulantJob(Pageable pageable){
        Page<PostulantJob> postulantJobPage = postulantJobService.getAllPostulantJob(pageable);
        List<PostulantJobResource> resources = postulantJobPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources,pageable, resources.size());
    }

    @Operation(summary="Get Postulant Job by Id", description="Get an specific postulant job given an Id", tags={"postulant_jobs"})
    @GetMapping("/postulantjobs/{postulantJobId}")
    public PostulantJobResource getPostulantJobById(
            @PathVariable Long postulantJobId) {
        return convertToResource(postulantJobService.getPostulantJobById(postulantJobId));
    }

    @Operation(summary="Get Postulant Job", description="Get all the postulant job related to an existing postulant Id", tags={"postulant_jobs"})
    @GetMapping("/postulants/{postulantId}/postulantjobs")
    public Page<PostulantJobResource> getAllPostulantJobByPostulantId(@PathVariable Long postulantId, Pageable pageable) {
        Page<PostulantJob> postulantJobPage = postulantJobService.getAllPostulantJobByPostulantId(postulantId, pageable);
        List<PostulantJobResource> resources = postulantJobPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary="Get Postulant Job", description="Get all the postulant job related to an existing job offer Id", tags={"postulant_jobs"})
    @GetMapping("/joboffers/{jobofferId}/postulantjobs")
    public Page<PostulantJobResource> getAllPostulantJobByJobOfferId(
            @PathVariable Long jobofferId,
            Pageable pageable) {
        Page<PostulantJob> postulantJobPage = postulantJobService.getAllPostulantJobByJobOfferId(jobofferId, pageable);
        List<PostulantJobResource> resources = postulantJobPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    private PostulantJob convertToEntity(SavePostulantJobResource resource){
        return mapper.map(resource, PostulantJob.class);
    }

    private PostulantJobResource convertToResource(PostulantJob entity){
        return mapper.map(entity,PostulantJobResource.class);
    }
}
