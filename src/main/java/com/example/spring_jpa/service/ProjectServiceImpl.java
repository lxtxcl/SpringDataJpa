package com.example.spring_jpa.service;

import com.example.spring_jpa.dao.ProjectRepository;
import com.example.spring_jpa.entity.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService{


    @Autowired
    ProjectRepository projectRepository;
    @Override
    public void save(Project project) {
        projectRepository.save(project);
    }

    @Override
    public Project findById(long id) {
        return projectRepository.findById(id).get();
    }

    @Override
    public void saveAll(List<Project> list) {
        projectRepository.saveAll(list);
    }

    @Override
    public void remove(long id) {
        projectRepository.deleteById(id);
    }

    @Override
    public List<Project> findAll() {
        return projectRepository.findAll();
    }
}
