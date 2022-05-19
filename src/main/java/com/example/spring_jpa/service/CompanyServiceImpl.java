package com.example.spring_jpa.service;

import com.example.spring_jpa.dao.CompanyRepository;
import com.example.spring_jpa.entity.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService{

    @Autowired
    CompanyRepository companyRepository;


    @Override
    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    @Override
    public void save(Company company) {
         companyRepository.save(company);
    }

    @Transactional
    @Override
    public void remove(long id) {
        companyRepository.deleteById(id);
    }

    @Override
    public void saveAll(List<Company> list) {
        companyRepository.saveAll(list);
    }

    @Override
    public Company findById(long id) {
        return companyRepository.findById(id).get();
    }

    @Override
    public List<Company> findByName(String name) {
        return companyRepository.findByName(name);
    }
}
