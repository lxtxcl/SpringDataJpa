package com.example.spring_jpa.entity;

import com.example.spring_jpa.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;


import java.util.Date;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class Employee {

    int age;
    @DateTimeFormat(pattern = DateUtils.YYYY_MM_DD_HH_MM_SS)
    @JsonFormat(pattern = DateUtils.YYYY_MM_DD_HH_MM_SS,timezone = "GMT+8")
    Date time;

    boolean flag;

    String size;

    private String name;
}
