package com.example.jobagapi.controller;

import com.example.jobagapi.domain.model.Languages;
import com.example.jobagapi.domain.model.Sector;
import com.example.jobagapi.domain.model.Studies;
import com.example.jobagapi.domain.service.StudiesService;
import com.example.jobagapi.resource.*;
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
public class StudiesController {
    @Autowired
    private StudiesService studiesService;
    @Autowired
    private ModelMapper mapper;

    @Operation(summary="Update Studies", description="Update an already existing study", tags={"studies"})
    @PutMapping("/studies/{studyId}")
    public StudiesResource updateStudies(@PathVariable Long studyId, @Valid @RequestBody SaveStudiesResource resource){
        Studies study = convertToEntity(resource);
        return convertToResource(studiesService.updateStudies(studyId,study));
    }

    @Operation(summary = "Get Studies", description = "Get all the studies registered in the database", tags={"studies"})
    @GetMapping("/studies")
    public Page<StudiesResource> getAllStudies(Pageable pageable){
        Page<Studies> studiesPage = studiesService.getAllStudies(pageable);
        List<StudiesResource> resources = studiesPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources,pageable, resources.size());
    }

    @Operation(summary = "Post Studies", description = "Create a new study", tags={"studies"})
    @PostMapping("/studies")
    public  StudiesResource createStudies(@Valid @RequestBody SaveStudiesResource resource){
        Studies studies = convertToEntity(resource);
        return convertToResource(studiesService.createStudies(studies));
    }

    @Operation(summary = "Get Studies by Id", description = "Get an study given an specific Id", tags={"studies"})
    @GetMapping("/studies/{studyId}")
    public StudiesResource getStudiesById(@PathVariable Long studyId){
        return convertToResource(studiesService.getStudiesById(studyId));
    }
    @Operation(summary = "Delete Studies", description = "Delete an already existing study", tags={"studies"})
    @DeleteMapping("/studies/{studyId}")
    public ResponseEntity<?> deleteStudies(@PathVariable Long studyId) {
        return studiesService.deleteStudies(studyId);
    }

    private Studies convertToEntity(SaveStudiesResource resource){
        return mapper.map(resource, Studies.class);
    }

    private StudiesResource convertToResource(Studies entity){
        return mapper.map(entity, StudiesResource.class);
    }

}

