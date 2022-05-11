package com.example.spring_jpa.repositories;

import com.example.spring_jpa.pojo.JsonInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;


@Component
public interface JsonInfoRepository extends JpaRepository<JsonInfo,Long> {

    @Query(value = "select * from test_json where vname=:name ",nativeQuery = true)
    Iterable<JsonInfo> findByEmployeeName(String name);
}
