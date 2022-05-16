package com.example.spring_jpa;

import com.example.spring_jpa.entity.JacksonInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class JacksonTest {


    @Test
    public void testJackSon() throws JsonProcessingException {
        ObjectMapper mapper=new ObjectMapper();
        //mapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
        JacksonInfo jacksonTest=new JacksonInfo("a","ignore",LocalDateTime.now());
        jacksonTest.setIgnore("ignore");
        String text=mapper.writeValueAsString(jacksonTest);
        System.out.println("text:"+text);

        JacksonInfo jacksonInfo1 =mapper.readValue(text, JacksonInfo.class);
        System.out.println("jacksonTest:"+ jacksonInfo1);


    }
}
