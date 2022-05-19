package com.example.spring_jpa.rest;

import com.example.spring_jpa.entity.Project;

import com.example.spring_jpa.entity.Staff;
import com.example.spring_jpa.service.ProjectService;
import com.example.spring_jpa.service.StaffService;
import com.example.spring_jpa.utils.JsonUtils;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("project")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @Autowired
    StaffService staffService;

    @PostMapping
    public void addProject(@RequestParam String name) {
        projectService.save(Project.builder().name(name).build());
    }

    @PutMapping
    public void updateProject(@RequestBody Project project) {
        projectService.save(project);
    }

    @DeleteMapping
    public void deleteProject(@RequestParam long id) {
        projectService.remove(id);
    }

    @GetMapping
    public JsonNode getProject() {
        List<Project> list = projectService.findAll();
        return JsonUtils.object().set("result", JsonUtils.toJson(list));
    }

    @PutMapping("staff")
    public void addStaff(@RequestParam long staffId, @RequestParam long projectId){
        Project project= projectService.findById(projectId);
        project.getStaffs().add(staffService.findById(staffId));
        projectService.save(project);
    }

    @DeleteMapping("staff")
    public void deleteStaff(@RequestParam long staffId, @RequestParam long projectId){
        Project project= projectService.findById(projectId);
        project.setStaffs(project.getStaffs().stream().filter(p -> p.getId() != staffId).collect(Collectors.toList()));
        projectService.save(project);
    }
}
