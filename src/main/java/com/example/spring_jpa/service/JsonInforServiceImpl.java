package com.example.spring_jpa.service;

import com.example.spring_jpa.common.JsonPathType;
import com.example.spring_jpa.common.JsonQueryMatch;
import com.example.spring_jpa.entity.Employee;
import com.example.spring_jpa.entity.JsonInfo;
import com.example.spring_jpa.dao.JsonInfoRepository;
import com.example.spring_jpa.utils.JsonUtil;
import com.example.spring_jpa.utils.JsonUtils;
import com.fasterxml.jackson.databind.JsonNode;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class JsonInforServiceImpl implements JsonInfoService {
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


    @Override
    public Iterable<JsonInfo> findByJsonKeysExists(JsonPathType type, List<String> path) {
        List<String> truePath = path.stream().map(JsonUtils::stringToPath).collect(Collectors.toList());
        return jsonInfoRepository.findByJsonKeysExists(truePath, type.getVal());
    }

    @Override
    public Iterable<JsonInfo> findByJsonKeyValueExists( String path,Object value) {
        String val = JsonUtils.format(JsonUtils.toJson(value));
        return jsonInfoRepository.findByJsonKeyVelueExists(JsonUtils.stringToPath(path), val);
    }

    @Override
    public Iterable<JsonInfo> findByJsonPathIndexExists(List<String> path) {

        List<String> truePath = path.stream().map(JsonUtils::stringToPath).collect(Collectors.toList());
        return jsonInfoRepository.findByJsonPathIndexExists(truePath);
    }

    @Override
    public Iterable<String> findInfoByJsonPath(List<String> path) {
        List<String> truePath = path.stream().map(JsonUtils::stringToPath).collect(Collectors.toList());
        return jsonInfoRepository.findInfoByJsonPath(truePath);
    }

    @Override
    public Iterable<JsonInfo> findByJsonValueBetween(String path, String min, String max) {
        //return jsonInfoRepository.findbyJsonValueBetween("$."+path,JsonUtils.format(JsonUtils.toJson(min)),JsonUtils.format(JsonUtils.toJson(max)));
        return jsonInfoRepository.findByJsonValueBetween(JsonUtils.stringToPath(path), min, max);
    }

    @Override
    public Iterable<JsonInfo> findByJsonValueAfter(String path, String min) {
        return jsonInfoRepository.findByJsonValueAfter(JsonUtils.stringToPath(path), min);
    }

    @Override
    public Iterable<JsonInfo> findByJsonValueBefore(String path, String max) {
        return jsonInfoRepository.findByJsonValueBefore(JsonUtils.stringToPath(path), max);
    }

    @Override
    public Iterable<JsonInfo> findByJsonValueLike(String path, String left, String mid, String right) {
        return jsonInfoRepository.findByJsonValueLike(JsonUtils.stringToPath(path), left, mid, right);
    }

    @Transactional
    @Override
    public void allJsonSet(Map<String, Object> map) {
        List<Object> list = new ArrayList<>();
        map.entrySet().forEach(entry -> {
            list.add(JsonUtils.stringToPath(entry.getKey()));
            list.add(entry.getValue());
        });
        jsonInfoRepository.allJsonSet(list);
    }

    @Transactional
    @Override
    public void allJsonInsert(Map<String, Object> map) {
        List<Object> list = new ArrayList<>();
        map.entrySet().forEach(entry -> {
            list.add(JsonUtils.stringToPath(entry.getKey()));
            if(entry.getValue() instanceof  String){
                list.add(entry.getValue());
            }else {
                //list.add(JsonUtils.format(JsonUtils.toJson(entry.getValue())));
                list.add(JsonUtils.toJson(entry.getValue()).toPrettyString());
                System.out.println(JsonUtils.toJson(entry.getValue()).toPrettyString());
                //list.add(JsonUtil.toJson(entry.getValue()).toJSONString());
            }

        });
        jsonInfoRepository.allJsonInsert(list);
    }

    @Transactional
    @Override
    public void allJsonReplace(Map<String, Object> map) {
        List<Object> list = new ArrayList<>();
        map.entrySet().forEach(entry -> {
            list.add(JsonUtils.stringToPath(entry.getKey()));
            list.add(JsonUtils.toJson(entry.getValue()).toString());
        });
        /*List<Object> list = new ArrayList<>();
        map.entrySet().forEach(entry -> {
            list.add(JsonUtils.stringToPath(entry.getKey()));
            if(entry.getValue() instanceof  String){
                list.add(entry.getValue());
            }else {
                //list.add(JsonUtils.format(JsonUtils.toJson(entry.getValue())));
                //list.add(JsonUtils.toJson(entry.getValue()).toString());
                //System.out.println(JsonUtils.toJson(entry.getValue()).toPrettyString());

            }

        });*/
        jsonInfoRepository.allJsonReplace(list);
    }

    @Transactional
    @Override
    public void allJsonRemoveByPath(List<String> lists) {
        List<String> truePath = lists.stream().map(JsonUtils::stringToPath).collect(Collectors.toList());
        jsonInfoRepository.allJsonRemoveByPath(truePath);
    }

    @Override
    public Iterable<JsonInfo> findByJsonVelueExists(Object value) {
        return jsonInfoRepository.findByJsonVelueExists(JsonUtils.format(JsonUtils.toJson(value)));
    }
    @Override
    public Iterable<JsonInfo> findByJsonArrayVelueAnyExists(Object value){

        if(value instanceof List){
            return jsonInfoRepository.findByJsonArrayVelueAnyExists((List<Object>) value);
        }else if(value instanceof Map){
            List<Object> list=new ArrayList<>();
            ((Map<Object, Object>) value).entrySet().forEach(e->{
                list.add(e.getKey());
                list.add(e.getValue());
            });

            return jsonInfoRepository.findByJsonObjectVelueAnyExists(list);
        }else {
            return null;
        }

    }
}
