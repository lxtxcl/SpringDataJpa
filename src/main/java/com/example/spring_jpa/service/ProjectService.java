package com.example.spring_jpa.service;

import com.example.spring_jpa.entity.Project;

import java.util.List;

public interface ProjectService {



    void save(Project project);

    Project findById(long id);

    void saveAll(List<Project>list);

    void remove(long id);

    List<Project> findAll();
}
