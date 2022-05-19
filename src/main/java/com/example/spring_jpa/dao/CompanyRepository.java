package com.example.spring_jpa.dao;

import com.example.spring_jpa.entity.Company;
import com.example.spring_jpa.entity.JsonInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Long> {


    List<Company> findByName(String name);
}
