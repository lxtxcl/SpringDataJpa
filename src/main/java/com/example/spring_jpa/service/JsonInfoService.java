package com.example.spring_jpa.service;

import com.example.spring_jpa.common.JsonPathType;
import com.example.spring_jpa.common.JsonQueryMatch;
import com.example.spring_jpa.entity.Employee;
import com.example.spring_jpa.entity.JsonInfo;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;
import java.util.Map;
import java.util.Optional;


public interface JsonInfoService {

    Iterable<JsonInfo> findAll();

    void addJson(JsonInfo info);

    Optional<JsonInfo> findOne(Long id);

    void remove(Long id);

    Iterable<JsonInfo> findByEmployeeName(Employee employee);

    //查询存在path路径的json
    Iterable<JsonInfo> findByJsonKeysExists(JsonPathType type, List<String> path);

    //查询存在path路径下对应值的json
    Iterable<JsonInfo> findByJsonKeyValueExists( String path,Object value);

    //查询存在path路径对应下标的数据
    Iterable<JsonInfo> findByJsonPathIndexExists(List<String> path);

    Iterable<String> findInfoByJsonPath(List<String> path);


    Iterable<JsonInfo> findByJsonValueBetween(String path,String min,String max);

    Iterable<JsonInfo> findByJsonValueAfter(String path,String min);

    Iterable<JsonInfo> findByJsonValueBefore(String path,String max);


    Iterable<JsonInfo> findByJsonValueLike(String path, String left, String mid,String right);

    void allJsonSet(Map<String,Object> map);

    void allJsonInsert(Map<String,Object> map);

    void allJsonReplace(Map<String,Object> map);

    void allJsonRemoveByPath(List<String> lists);

    Iterable<JsonInfo> findByJsonVelueExists(Object value);

    Iterable<JsonInfo> findByJsonArrayVelueAnyExists(Object value);
}
