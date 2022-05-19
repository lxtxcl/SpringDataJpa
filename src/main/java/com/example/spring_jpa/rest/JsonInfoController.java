package com.example.spring_jpa.rest;

import com.example.spring_jpa.dto.Employee;
import com.example.spring_jpa.entity.JsonInfo;
import com.example.spring_jpa.service.JsonInfoService;
import com.example.spring_jpa.utils.JsonUtils;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("json-info")
public class JsonInfoController {

    @Autowired
    JsonInfoService jsonInfoService;

    /*数据库中初始化10000条数据*/
    @PostMapping("init")
    public void initDatabase() {
        List<JsonInfo> list = new ArrayList<>();

        for (int i = 0; i < 10000; i++) {
            Employee employee = Employee.builder()
                    .age(i / 100)
                    .flag(i % 2 == 0 ? true : false)
                    .time(new Date())
                    .name("z" + i % 100)
                    .size(String.valueOf(i % 100))
                    .build();

            List<Employee> s = new ArrayList<>();
            s.add(employee);
            Employee employee2 = Employee.builder()
                    .age(i / 100 + 1)
                    .flag(i % 2 == 0 ? true : false)
                    .time(new Date())
                    .name("z" + (i % 100 + 1))
                    .size(String.valueOf(i % 100 + 1))
                    .build();
            s.add(employee2);
            JsonInfo jsonInfo = JsonInfo.builder()
                    .info(JsonUtils.toJson(employee))
                    .msg(JsonUtils.toJson(s))
                    .build();
            list.add(jsonInfo);
        }
        jsonInfoService.addAllJson(list);
    }

    /*读取所有数据*/
    @GetMapping
    public JsonNode readAll() {
        List<JsonInfo> infos = jsonInfoService.findAll();

        return JsonUtils.object().set("result", JsonUtils.toJson(infos));
    }

    /*查询所有key都存在的数据*/
    @GetMapping("contains-all")
    public JsonNode getContainsAllKeys(@RequestParam List<String> keys) {
        List<JsonInfo> infos = jsonInfoService.findByAllJsonKeysExists(keys);
        return JsonUtils.object().set("result", JsonUtils.toJson(infos));
    }

    /*查询key存在的数据*/
    @GetMapping("contains-any")
    public JsonNode getContainsAnyKeys(@RequestParam List<String> keys) {
        List<JsonInfo> infos = jsonInfoService.findByAnyJsonKeysExists(keys);
        return JsonUtils.object().set("result", JsonUtils.toJson(infos));
    }

    /*查询存在相同key-value的数据*/
    @GetMapping("contains-key-value")
    public JsonNode GetByContainsKeys(@RequestParam String key, @RequestParam Object value) {

        List<JsonInfo> infos = jsonInfoService.findByJsonKeyValueExists(key, value);
        return JsonUtils.object().set("result", JsonUtils.toJson(infos));
    }

    /*查询所有key的value*/
    @GetMapping("values")
    public JsonNode GetValueByKeys(@RequestParam List<String> keys) {

        List<String> infos = jsonInfoService.findInfoByJsonPath(keys);
        return JsonUtils.object().set("result", JsonUtils.toJson(infos));
    }

    /*查询值在max,min之间的数据*/
    @GetMapping("value-between")
    public JsonNode GetValueBetween(@RequestParam String min, @RequestParam String max, @RequestParam String path) {
        List<JsonInfo> infos = jsonInfoService.findByJsonValueBetween(path, min, max);
        return JsonUtils.object().set("result", JsonUtils.toJson(infos));
    }

    /*查询值>after 以及值小于before的数据*/
    @GetMapping("value-before-After")
    public JsonNode GetValueBeforeAndAfter(@RequestParam String before, @RequestParam String after, @RequestParam String path) {
        List<JsonInfo> afters = jsonInfoService.findByJsonValueAfter(path, after);
        List<JsonInfo> befores = jsonInfoService.findByJsonValueBefore(path, before);
        Map<String, List<JsonInfo>> rst = new HashMap<>();
        rst.put("afters", afters);
        rst.put("befores", befores);
        return JsonUtils.object().set("result", JsonUtils.toJson(rst));
    }

    /*模糊查询*/
    @GetMapping("value-like")
    public JsonNode getValueLike(@RequestParam String path, @RequestParam String prefix, @RequestParam String infill, @RequestParam String suffix) {
        if (prefix == null) {
            prefix = "";
        }
        if (suffix == null) {
            suffix = "";
        }
        List<JsonInfo> infos = jsonInfoService.findByJsonValueLike(path, prefix, infill, suffix);
        return JsonUtils.object().set("result", JsonUtils.toJson(infos));
    }

    /*查询数组中存在对应value的数据*/
    @GetMapping("contains-values")
    public JsonNode getByArrayValues(@RequestParam List<Object> values) {
        List<JsonInfo> infos = jsonInfoService.findByArrayValues(values);
        return JsonUtils.object().set("result", JsonUtils.toJson(infos));
    }
}


