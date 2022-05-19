package com.example.spring_jpa.dao;


import com.example.spring_jpa.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {


}
