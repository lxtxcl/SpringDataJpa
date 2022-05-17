package com.example.spring_jpa.service;

import com.example.spring_jpa.entity.JsonInfo;

import java.util.List;
import java.util.Map;
import java.util.Optional;


public interface JsonInfoService {

    List<JsonInfo> findAll();

    void addAllJson(List<JsonInfo> list);

    void addJson(JsonInfo info);

    Optional<JsonInfo> findOne(Long id);

    void remove(Long id);


    //查询存在所有path路径的json
    List<JsonInfo> findByAllJsonKeysExists(List<String> path);

    //查询存在path路径的json
    List<JsonInfo> findByAnyJsonKeysExists(List<String> path);

    //查询存在path路径下对应值的json
    List<JsonInfo> findByJsonKeyValueExists(String path, Object value);

    //查询存在path路径对应下标的数据
    List<JsonInfo> findByJsonPathIndexExists(List<String> path);

    List<String> findInfoByJsonPath(List<String> path);


    List<JsonInfo> findByJsonValueBetween(String path, String min, String max);

    List<JsonInfo> findByJsonValueAfter(String path, String min);

    List<JsonInfo> findByJsonValueBefore(String path, String max);


    List<JsonInfo> findByJsonValueLike(String path, String prefix, String infill, String suffix);

    void allJsonSet(Map<String, Object> map);

    void allJsonInsert(Map<String, Object> map);

    void allJsonReplace(Map<String, Object> map);

    void allJsonRemoveByPath(List<String> lists);

    List<JsonInfo> findByJsonVelueExists(Object value);


    List<JsonInfo> findByArrayValues(List<Object> value);
}
