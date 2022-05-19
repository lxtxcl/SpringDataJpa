package com.example.spring_jpa;

import com.example.spring_jpa.entity.Company;
import com.example.spring_jpa.entity.Project;
import com.example.spring_jpa.entity.Staff;
import com.example.spring_jpa.service.CompanyService;
import com.example.spring_jpa.service.ProjectService;
import com.example.spring_jpa.service.StaffService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.stream.Collectors;

@SpringBootTest
public class JpaTest {

    @Autowired
    CompanyService companyService;

    @Autowired
    StaffService staffService;

    @Autowired
    ProjectService projectService;

    @Test
    public void addCompany() {
        //Company company=Company.builder().name("电信6").build();
        //.save(company);
        /*Company company = companyService.findById(8);
        Staff staff=staffService.findById(9L);
        company.getStaffs().remove(staff);
        companyService.save(company);*/
        Company company=Company.builder().name("电信j").build();
        //companyService.save(company);
        List<Staff> list=new ArrayList<>();
        list.add(Staff.builder().name("张1").build());
        list.add(Staff.builder().name("张2").build());
        company.setStaffs(list);
        companyService.save(company);
    }

    @Test
    public void removeCompany() {
        Project project=Project.builder().name("项目15").build();
        projectService.save(project);
        Staff staff=staffService.findById(13);
        staff.getProjects().add(project);
        staffService.save(staff);
    }

    @Test
    public void getCompany() {
        String name = "电信";
        List<Company> list = companyService.findByName(name);
        list.forEach(System.out::println);
    }


    @Test
    public void getStaff() {
        Staff staff = staffService.findById(3L);
        //staff.setProjects(projectService.findAll());
        //staffService.save(staff);
        Project project=projectService.findById(3L);
        project.getStaffs().forEach(System.out::println);
    }

    @Test
    public void saveProject() {

        List<Project> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(Project.builder().name("项目" + i).build());
        }
        projectService.saveAll(list);
        Staff staff = staffService.findById(9L);
        staff.setProjects(list);
        staffService.save(staff);
    }

    @Test
    public void removeProject() {
        projectService.remove(2L);
        /*Staff staff=staffService.findById(9L);
        staff.getProjects().remove(0);
        staffService.save(staff);*/
    }

    @Test
    public void findStaffsByProject(){
        Project project=projectService.findById(4L);
        List<Staff> list=staffService.findAllByProject(project);
        list.forEach(s->{
            List<Project> rst=s.getProjects().stream().filter(r->r.getId()!=4L).collect(Collectors.toList());
            s.setProjects(rst);
        });
        staffService.saveAll(list);
    }
}
