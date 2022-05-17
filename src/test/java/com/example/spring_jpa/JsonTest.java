package com.example.spring_jpa;

import com.example.spring_jpa.dto.Employee;
import com.example.spring_jpa.entity.JsonInfo;
import com.example.spring_jpa.service.JsonInforServiceImpl;
import com.example.spring_jpa.utils.DateUtils;
import com.example.spring_jpa.utils.JsonUtils;
import com.fasterxml.jackson.databind.JsonNode;
import org.apache.commons.lang3.StringEscapeUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@SpringBootTest
public class JsonTest {
    @Autowired
    JsonInforServiceImpl jsonInfoService;


    @Test
    public void simpleRead() {
        List<JsonInfo> infos = jsonInfoService.findAll();
        //infos.forEach(System.out::println);
        for (JsonInfo info : infos) {
            System.out.println(info.getInfo());
            //JacksonInfo jacksonInfo = JsonUtils.to(info.getInfo(), JacksonInfo.class);
            //Employee jacksonInfo = JsonUtils.to(info.getInfo(), Employee.class);
            //System.out.println(jacksonInfo);
            //System.out.println(jacksonInfo.getTime());
        }
        //JsonInfo jsonInfo=JsonInfo.builder().id(1L).build();
        //System.out.println(JsonUtils.format(JsonUtils.toJson(jsonInfo)));

    }

    @Test
    public void simpleAdd() {

        JsonNode info = JsonUtils
                .toJson(Employee.builder().name("三").size(null).flag(false).time(new Date())
                        .build());
        JsonInfo jsonInfo = JsonInfo.builder()
                .info(info).build();

        jsonInfoService.addJson(jsonInfo);
    }

    @Test
    public void simpleUpdate() {
        JsonInfo json_info = jsonInfoService.findOne(4L).get();
        /*Map<String,String> map=json_info.getInfo();
        map.put("age","5");
        map.remove("time");
        json_info.setInfo(map);
        jsonInfoService.addJson(json_info);*/
    }

    @Test
    public void simpleDelete() {

        jsonInfoService.remove(4L);
    }

    /*@Test
    public void testIndex() {
        System.out.println(jsonInfoService.findByEmployeeName(Employee.builder().name("\"三\"").build()));
    }*/

    @Test
    public void GetByContainsKeys() {
        List<String> list = new ArrayList<>();
        list.add("size");
        list.add("aaa");
        List<JsonInfo> infos = jsonInfoService.findByAnyJsonKeysExists(list);
        infos.forEach(System.out::println);

    }

    @Test
    public void getByContainsKeyValue() {
        Map<String, Object> map = new HashMap<>();
        map.put("a", "1");
        map.put("b", "4");

        List<JsonInfo> infos = jsonInfoService.findByJsonKeyValueExists("time", "2022-05-11 10:54:50");
        //List<JsonInfo> infos=jsonInfoService.findByJsonKeyValueExists("size",map);
        infos.forEach(System.out::println);
    }

    @Test
    public void getByContainsValue() {
        //int[] value=new int[]{1};
        Map<String, Object> value = new HashMap<>();
        value.put("size", null);
        List<JsonInfo> infos = jsonInfoService.findByJsonVelueExists(value);
        infos.forEach(System.out::println);
    }

    @Test
    public void getByJsonPathIndex() {
        //String s1="2.*";
        String s2 = "age";
        List<String> list = new ArrayList<>();
        //list.add(s1);
        list.add(s2);
        List<JsonInfo> infos = jsonInfoService.findByJsonPathIndexExists(list);
        infos.forEach(System.out::println);
    }

    @Test
    public void getInfoByJsonPath() {
        String s1 = "aaa";
        String s2 = "size";
        List<String> list = new ArrayList<>();
        list.add(s1);
        list.add(s2);
        List<String> infos = jsonInfoService.findInfoByJsonPath(list);
        infos.forEach(p -> {
            //System.out.println(JsonUtils.to(p,HashMap.class));
            System.out.println(p);
        });

    }

    @Test
    public void getByJsonValueBetween() {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(DateUtils.YYYY_MM_DD_HH_MM_SS);
        //List<JsonInfo> infos=jsonInfoService.findbyJsonValueBetween("age","1","20");
        String minTime = "2022-04-10 14:49:14";
        LocalDateTime min = LocalDateTime.parse(minTime, df);
        String minT = df.format(min);
        String maxTime = "2022-06-11 17:43:41";
        LocalDateTime max = LocalDateTime.parse(maxTime, df);
        String maxT = df.format(max);
        List<JsonInfo> infos = jsonInfoService.findByJsonValueBetween("age", "0", "5");
        infos.forEach(System.out::println);
    }

    @Test
    public void getByJsonValueBeforeAndAfter() {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(DateUtils.YYYY_MM_DD_HH_MM_SS);

        String time = "2022-05-11 10:54:50";
        LocalDateTime min = LocalDateTime.parse(time, df);
        String t = df.format(min);
        List<JsonInfo> after = jsonInfoService.findByJsonValueAfter("time", t);
        System.out.println("after:");
        after.forEach(System.out::println);
        List<JsonInfo> before = jsonInfoService.findByJsonValueBefore("time", t);
        System.out.println("before:");
        before.forEach(System.out::println);
    }


    @Test
    public void getByJsonValueLike() {
        List<JsonInfo> infos = jsonInfoService.findByJsonValueLike("name", "%", "三", "%");
        infos.forEach(System.out::println);
    }


    @Test
    public void allJsonUpdate() {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> map2 = new HashMap<>();
        map2.put("age", "14");
        //map.put("pides",JacksonInfo.builder().name("2").build());
        map.put("pides", map2);
        //jsonInfoService.allJsonSet(map);
        //jsonInfoService.allJsonInsert(map);
        jsonInfoService.allJsonReplace(map);
    }

    @Test
    public void allJsonRemoveByPath() {
        List<String> list = new ArrayList<>();
        list.add("pides");
        jsonInfoService.allJsonRemoveByPath(list);
    }

    @Test
    public void findByJsonVelueAnyExists() {
        List<Object> list = new ArrayList<>();
        list.add(1);
        list.add(2);

        int[] ints = new int[]{1, 2};

        Map<String, Object> map = new HashMap<>();
        map.put("name", "三");
        map.put("size", 5);
        /*List<JsonInfo> infos = jsonInfoService.findByJsonArrayVelueAnyExists(map);
        infos.forEach(p->{
            //System.out.println(JsonUtils.to(p,HashMap.class));
            System.out.println(StringEscapeUtils.unescapeJson(JsonUtils.format(p.getInfo())));
            //System.out.println(JsonUtils.format(p.getInfo()));
        });*/
    }

    @Test
    public void findByArrayValues() {

        List<Object> value = new ArrayList<>();
        value.add("zz");
        value.add("bb");
        List<JsonInfo> list = jsonInfoService.findByArrayValues(value);
        list.forEach(System.out::println);
    }
}
