package com.example.spring_jpa.service;

import com.example.spring_jpa.pojo.Employee;
import com.example.spring_jpa.pojo.JsonInfo;
import com.example.spring_jpa.repositories.JsonInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JsonInforService implements JsonInfoServiceImpl{
    @Autowired
    JsonInfoRepository jsonInfoRepository;
    @Override
    public Iterable<JsonInfo> findAll() {
        return jsonInfoRepository.findAll();
    }

    @Override
    public void addJson(JsonInfo info) {
        jsonInfoRepository.save(info);

    }

    @Override
    public Optional<JsonInfo> findOne(Long id) {
        return jsonInfoRepository.findById(id);
    }

    @Override
    public void remove(Long id) {
        jsonInfoRepository.deleteById(id);
    }

    @Override
    public Iterable<JsonInfo> findByEmployeeName(Employee employee) {
        return jsonInfoRepository.findByEmployeeName(employee.getName());

    }


}
