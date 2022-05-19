package com.example.spring_jpa.dao;

import com.example.spring_jpa.entity.JsonInfo;
import com.example.spring_jpa.entity.Project;
import com.example.spring_jpa.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StaffRepository extends JpaRepository<Staff, Long> {

    List<Staff> findAllByProjectsIs(Project project);


}
