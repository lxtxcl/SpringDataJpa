package com.example.spring_jpa.service;

import com.example.spring_jpa.pojo.Employee;
import com.example.spring_jpa.pojo.JsonInfo;

import java.util.Optional;


public interface JsonInfoServiceImpl {

    Iterable<JsonInfo> findAll();

    void addJson(JsonInfo info);

    Optional<JsonInfo> findOne(Long id);

    void remove(Long id);

    Iterable<JsonInfo> findByEmployeeName(Employee employee);
}
