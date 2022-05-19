package com.example.spring_jpa.service;

import com.example.spring_jpa.entity.JsonInfo;
import com.example.spring_jpa.dao.JsonInfoRepository;
import com.example.spring_jpa.utils.JsonUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class JsonInfoServiceImpl implements JsonInfoService {
    @Autowired
    JsonInfoRepository jsonInfoRepository;

    @Override
    public List<JsonInfo> findAll() {
        return jsonInfoRepository.findAll();
    }

    @Override
    public void addJson(JsonInfo info) {
        jsonInfoRepository.save(info);


    }

    @Override
    public void addAllJson(List<JsonInfo> list) {
        jsonInfoRepository.saveAll(list);
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
    public List<JsonInfo> findByAllJsonKeysExists(List<String> path) {
        List<String> truePath = path.stream().map(JsonUtils::stringToPath).collect(Collectors.toList());
        return jsonInfoRepository.findByAllJsonKeysExists(truePath);
    }

    @Override
    public List<JsonInfo> findByAnyJsonKeysExists(List<String> path) {
        List<String> truePath = path.stream().map(JsonUtils::stringToPath).collect(Collectors.toList());
        return jsonInfoRepository.findByAnyJsonKeysExists(truePath);
    }

    @Override
    public List<JsonInfo> findByJsonKeyValueExists(String path, Object value) {

        // String val = JsonUtils.format(JsonUtils.toJson(value));
        //return jsonInfoRepository.findByJsonKeyVelueExists(JsonUtils.stringToPath(path), val);
        return jsonInfoRepository.findByJsonKeyVelueExists(path, value);
    }

    @Override
    public List<JsonInfo> findByJsonPathIndexExists(List<String> path) {

        List<String> truePath = path.stream().map(JsonUtils::stringToPath).collect(Collectors.toList());
        return jsonInfoRepository.findByJsonPathIndexExists(truePath);
    }

    @Override
    public List<String> findInfoByJsonPath(List<String> path) {
        List<String> truePath = path.stream().map(JsonUtils::stringToPath).collect(Collectors.toList());
        return jsonInfoRepository.findInfoByJsonPath(truePath);
    }

    @Override
    public List<JsonInfo> findByJsonValueBetween(String path, String min, String max) {
        //return jsonInfoRepository.findbyJsonValueBetween("$."+path,JsonUtils.format(JsonUtils.toJson(min)),JsonUtils.format(JsonUtils.toJson(max)));
        if (NumberUtils.isCreatable(min) && NumberUtils.isCreatable(max)) {
            return jsonInfoRepository.findByJsonValueBetween(path, Integer.parseInt(min), Integer.parseInt(max));
        } else {
            return jsonInfoRepository.findByJsonValueBetween(path, min, max);
        }
    }

    @Override
    public List<JsonInfo> findByJsonValueAfter(String path, String after) {
        if (NumberUtils.isCreatable(after)) {
            return jsonInfoRepository.findByJsonValueAfter(path, Integer.parseInt(after));
        } else {
            return jsonInfoRepository.findByJsonValueAfter(path, after);
        }

    }

    @Override
    public List<JsonInfo> findByJsonValueBefore(String path, String before) {
        if (NumberUtils.isCreatable(before)) {
            return jsonInfoRepository.findByJsonValueBefore(path, Integer.parseInt(before));
        } else {
            return jsonInfoRepository.findByJsonValueBefore(path, before);
        }
    }

    @Override
    public List<JsonInfo> findByJsonValueLike(String path, String prefix, String infill, String suffix) {
        return jsonInfoRepository.findByJsonValueLike(path, prefix, infill, suffix);
    }

    @Transactional
    @Override
    public void allJsonSetString(Map<String, Object> map) {
        List<Object> list = new ArrayList<>();
        map.entrySet().forEach(entry -> {
            list.add(JsonUtils.stringToPath(entry.getKey()));
            list.add(entry.getValue());
        });
        jsonInfoRepository.allJsonSetString(list);
    }

    @Transactional
    @Override
    public void allJsonInsertString(Map<String, Object> map) {
        List<Object> list = new ArrayList<>();
        map.entrySet().forEach(entry -> {
            list.add(JsonUtils.stringToPath(entry.getKey()));
            list.add(entry.getValue());
        });
        jsonInfoRepository.allJsonInsertString(list);
    }

    @Transactional
    @Override
    public void allJsonReplaceString(Map<String, Object> map) {
        List<Object> list = new ArrayList<>();
        map.entrySet().forEach(entry -> {
            list.add(JsonUtils.stringToPath(entry.getKey()));
            list.add(entry.getValue());
        });
        jsonInfoRepository.allJsonReplaceString(list);
    }

    @Transactional
    @Override
    public void allJsonReplaceMap(String path,Map<String, Object> map) {
        List<Object> list = new ArrayList<>();
        map.entrySet().forEach(entry -> {
            list.add(JsonUtils.stringToPath(entry.getKey()));
            list.add(entry.getValue());
        });
        jsonInfoRepository.allJsonReplaceString(list);
    }
    @Transactional
    @Override
    public void allJsonRemoveByPath(List<String> lists) {
        List<String> truePath = lists.stream().map(JsonUtils::stringToPath).collect(Collectors.toList());
        jsonInfoRepository.allJsonRemoveByPath(truePath);
    }

    @Override
    public List<JsonInfo> findByJsonVelueExists(Object value) {
        return jsonInfoRepository.findByJsonVelueExists(JsonUtils.format(JsonUtils.toJson(value)));
    }



    @Override
    public List<JsonInfo> findByArrayValues( List<Object> value) {

        return jsonInfoRepository.findByArrayObjectValue( value);
    }
}
