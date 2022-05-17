package com.example.spring_jpa.dto;

import com.example.spring_jpa.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import org.springframework.format.annotation.DateTimeFormat;


import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@AllArgsConstructor
@Data
@NoArgsConstructor
@SuperBuilder
public class Employee {


    private int age;
    //@DateTimeFormat(pattern = DateUtils.YYYY_MM_DD_HH_MM_SS)
    //@JsonFormat(pattern = DateUtils.YYYY_MM_DD_HH_MM_SS,timezone = "GMT+8")
    //@Temporal(TemporalType.TIMESTAMP)
    private Date time;

    private boolean flag;

    private String size;

    private String name;

}
