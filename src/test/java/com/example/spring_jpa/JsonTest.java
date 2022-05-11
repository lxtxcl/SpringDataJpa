package com.example.spring_jpa;

import com.example.spring_jpa.pojo.Employee;
import com.example.spring_jpa.pojo.JsonInfo;
import com.example.spring_jpa.service.JsonInforService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class JsonTest {
    @Autowired
    JsonInforService jsonInfoService;


    @Test
    public void simpleRead(){
        System.out.println(jsonInfoService.findAll());
    }

    @Test
    public void simpleAdd(){

        /*Map<String,String> map=new HashMap<>();
        map.put("age","20");
        map.put("time","2022-1-1");
        Json_Info info=Json_Info.builder().info(map).build();
        */
        JsonInfo info= JsonInfo.builder().info(Employee.builder().name("三").time(new Date()).build()).build();
        jsonInfoService.addJson(info);
    }

    @Test
    public void simpleUpdate(){
        JsonInfo json_info=jsonInfoService.findOne(4L).get();
        /*Map<String,String> map=json_info.getInfo();
        map.put("age","5");
        map.remove("time");
        json_info.setInfo(map);
        jsonInfoService.addJson(json_info);*/
    }

    @Test
    public void simpleDelete(){
        jsonInfoService.remove(4L);
    }

    @Test
    public void testIndex(){
        System.out.println(jsonInfoService.findByEmployeeName(Employee.builder().name("\"三\"").build()));
    }
}
