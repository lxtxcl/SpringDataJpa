package com.example.spring_jpa.service;


import com.example.spring_jpa.entity.Project;
import com.example.spring_jpa.entity.Staff;

import java.util.List;
import java.util.Optional;

public interface StaffService {

    void save(Staff staff);

    Staff findById(long id);


    void remove(long id);

    List<Staff> findAllByProject(Project project);

    void saveAll(List<Staff> list);

    List<Staff> findAll();
}
