package com.example.spring_jpa.rest;

import com.example.spring_jpa.entity.Company;
import com.example.spring_jpa.entity.Staff;
import com.example.spring_jpa.service.CompanyService;
import com.example.spring_jpa.service.ProjectService;
import com.example.spring_jpa.service.StaffService;
import com.example.spring_jpa.utils.JsonUtils;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("staff")
public class StaffController {


    @Autowired
    StaffService staffService;

    @Autowired
    CompanyService companyService;

    @Autowired
    ProjectService projectService;


    @PostMapping
    public void addStaff(@RequestParam String name) {
        staffService.save(Staff.builder().name(name).build());
    }

    @PutMapping
    public void updateStaff(@RequestBody Staff staff) {
        staffService.save(staff);
    }


    @DeleteMapping
    public void deleteStaff(@RequestParam long id) {
        staffService.remove(id);
    }

    @GetMapping
    public JsonNode getStaff() {
        List<Staff> list = staffService.findAll();
        return JsonUtils.object().set("result", JsonUtils.toJson(list));
    }

    @PutMapping("company")
    public void changeCompany(@RequestParam long staffId, @RequestParam long companyId) {
        Staff staff = staffService.findById(staffId);
        staff.setCompany(companyService.findById(companyId));
        staffService.save(staff);
    }

    @PostMapping("project")
    public void addProject(@RequestParam long staffId, @RequestParam long projectId) {
        Staff staff = staffService.findById(staffId);
        staff.getProjects().add(projectService.findById(projectId));
        staffService.save(staff);
    }

    @DeleteMapping("project")
    public void deleteProject(@RequestParam long staffId, @RequestParam long projectId) {
        Staff staff = staffService.findById(staffId);
        staff.setProjects(staff.getProjects().stream().filter(p -> p.getId() != projectId).collect(Collectors.toList()));
        staffService.save(staff);
    }

}
