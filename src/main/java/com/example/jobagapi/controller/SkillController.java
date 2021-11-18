package com.example.jobagapi.controller;

import com.example.jobagapi.domain.model.Skill;
import com.example.jobagapi.resource.SkillResource;
import com.example.jobagapi.resource.SaveSkillResource;
import com.example.jobagapi.domain.service.SkillService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.modelmapper.ModelMapper;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class SkillController {
    @Autowired
    private SkillService skillService;

    @Autowired
    private ModelMapper mapper;

    @Operation(summary="Update Skill", description="Update an already existing skill", tags={"skills"})
    @PutMapping("/skills/{skillId}")
    public SkillResource updateSector(@PathVariable Long skillId, @Valid @RequestBody SaveSkillResource resource){
        Skill skill = convertToEntity(resource);
        return convertToResource(skillService.updateSkill(skillId,skill));
    }

    @Operation(summary="Get Skills", description="Get all the skill registered in the database", tags={"skills"})
    @GetMapping("/skills")
    public Page<SkillResource> getAllSkills(Pageable pageable){
        Page<Skill> skillPage = skillService.getAllSkills(pageable);
        List<SkillResource> resources = skillPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());

        return new PageImpl<>(resources, pageable, resources.size());
    }
    @Operation(summary="Post Skill", description="Create a new skill", tags={"skills"})
    @PostMapping("/skills")
    public SkillResource createSkill(@Valid @RequestBody SaveSkillResource resource) {
        Skill skill = convertToEntity(resource);
        return convertToResource(skillService.createSkill(skill));
    }

    @Operation(summary="Get Skill By Id", description="Get an skill given an specific Id", tags={"skills"})
    @GetMapping("/skills/{skillId}}")
    public SkillResource getSkillById(@PathVariable Long skillId) {
        return convertToResource(skillService.getSkillById(skillId));
    }

    @Operation(summary="Delete Skill", description="Delete an already existing skill", tags={"skills"})
    @DeleteMapping("/skills/{skillId}}")

    public ResponseEntity<?> deleteSkill(@PathVariable Long skillId) {
        return skillService.deleteSkill(skillId);
    }

    private Skill convertToEntity(SaveSkillResource resource) {
        return mapper.map(resource, Skill.class);
    }
    private SkillResource convertToResource(Skill entity)
    {
        return mapper.map(entity, SkillResource.class);
    }

}
