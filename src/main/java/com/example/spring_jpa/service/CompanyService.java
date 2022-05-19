package com.example.spring_jpa.service;

import com.example.spring_jpa.entity.Company;

import java.util.List;
import java.util.Optional;

public interface CompanyService {

     List<Company> findAll();

     void save(Company company);

     void remove(long id);

     void saveAll(List<Company> list);

     Company findById(long id);

     List<Company> findByName(String name);
}
