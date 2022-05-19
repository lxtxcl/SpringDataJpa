package com.example.spring_jpa.rest;

import com.example.spring_jpa.entity.Company;
import com.example.spring_jpa.entity.Staff;
import com.example.spring_jpa.service.CompanyService;
import com.example.spring_jpa.service.StaffService;
import com.example.spring_jpa.utils.JsonUtils;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("company")
public class CompanyController {

    @Autowired
    CompanyService companyService;
    @Autowired
    StaffService staffService;

    @GetMapping
    public JsonNode getCompany() {
        List<Company> list = companyService.findAll();
        return JsonUtils.object().set("result", JsonUtils.toJson(list));
    }

    @PostMapping
    public void addCompany(@RequestParam String name) {
        companyService.save(Company.builder().name(name).build());
    }

    @DeleteMapping
    public void deleteCompany(@RequestParam long id) {
        companyService.remove(id);
    }

    @PutMapping
    public void updateCompany(@RequestBody Company company) {
        companyService.save(company);
    }

    @PostMapping("staff")
    public void addStaff(@RequestParam long companyId, @RequestParam long staffId) {
        Company company = companyService.findById(companyId);
        Staff staff = staffService.findById(staffId);
        company.getStaffs().add(staff);
        companyService.save(company);
    }


    @DeleteMapping("staff")
    public void removeStaff(@RequestParam long companyId, @RequestParam long staffId) {
        Company company = companyService.findById(companyId);
        company.setStaffs(company.getStaffs().stream().filter(p -> p.getId() != staffId).collect(Collectors.toList()));
        companyService.save(company);
    }
}
