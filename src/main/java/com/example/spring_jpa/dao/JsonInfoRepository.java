package com.example.spring_jpa.dao;

import com.example.spring_jpa.entity.JsonInfo;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;


@Component
public interface JsonInfoRepository extends JpaRepository<JsonInfo, Long> {


    @Query(value = "select * from test_json where vname=:name ", nativeQuery = true)
    Iterable<JsonInfo> findByEmployeeName(String name);


    //匹配路径是否存在
    @Query(value = "SELECT * FROM test_json t where JSON_CONTAINS_PATH(t.info,:type, :path)>0", nativeQuery = true)
    Iterable<JsonInfo> findByJsonKeysExists(List<String> path, String type);

    //判断path路径下是否匹配对应值
    @Query(value = "SELECT * FROM test_json t where JSON_CONTAINS(t.info,:value, :path)>0", nativeQuery = true)
    Iterable<JsonInfo> findByJsonKeyVelueExists(String path, String value);

    //判断数组中是否匹配对应值,或是json对象中是否匹配对应键值对
    @Query(value = "SELECT * FROM test_json t where JSON_CONTAINS(t.info,:value)>0", nativeQuery = true)
    Iterable<JsonInfo> findByJsonVelueExists(String value);

    //判断数组是否存在值或键值对
    @Query(value = "SELECT * FROM test_json t where JSON_OVERLAPS(t.info,JSON_ARRAY(:value ))>0", nativeQuery = true)
    Iterable<JsonInfo> findByJsonArrayVelueAnyExists(List<Object> value);

    //判断object是否存在值或键值对
    @Query(value = "SELECT * FROM test_json t where JSON_OVERLAPS(t.info,JSON_Object(:value ))>0", nativeQuery = true)
    Iterable<JsonInfo> findByJsonObjectVelueAnyExists(List<Object> value);

    //查询存在path路径数据不为null的整条数据
    @Query(value = "SELECT * FROM test_json t where NOT ISNULL(JSON_EXTRACT(t.info,:path))", nativeQuery = true)
    Iterable<JsonInfo> findByJsonPathIndexExists(List<String> path);

    //查询存在path路径数据不为null的json
    @Query(value = "SELECT JSON_EXTRACT(t.info,:path) FROM test_json t where NOT ISNULL(JSON_EXTRACT(t.info,:path))", nativeQuery = true)
    Iterable<String> findInfoByJsonPath(List<String> path);

    //值between
    @Query(value = "SELECT * FROM test_json t where  JSON_UNQUOTE(JSON_EXTRACT(t.info,:path)) BETWEEN :min AND :max", nativeQuery = true)
    Iterable<JsonInfo> findByJsonValueBetween(String path, String min, String max);

    //值大于
    @Query(value = "SELECT * FROM test_json t where  JSON_UNQUOTE(JSON_EXTRACT(t.info,:path)) >:min", nativeQuery = true)
    Iterable<JsonInfo> findByJsonValueAfter(String path, String min);

    //值小于
    @Query(value = "SELECT * FROM test_json t where  JSON_UNQUOTE(JSON_EXTRACT(t.info,:path)) <:max", nativeQuery = true)
    Iterable<JsonInfo> findByJsonValueBefore(String path, String max);

    //值相等
    @Query(value = "SELECT * FROM test_json t where  JSON_UNQUOTE(JSON_EXTRACT(t.info,:path)) =:value", nativeQuery = true)
    Iterable<JsonInfo> findByJsonValueEquals(String path, Object value);


    //对应path下模糊查询匹配的数据
    @Query(value = "SELECT * FROM test_json t where  JSON_UNQUOTE(JSON_EXTRACT(t.info,:path)) Like CONCAT(:left,:mid,:right)", nativeQuery = true)
    Iterable<JsonInfo> findByJsonValueLike(String path, String left, String mid, String right);

    //有就修改 没有就新增
    @Modifying
    @Query(value = "UPDATE test_json SET info = JSON_SET(info, :lists) ;", nativeQuery = true)
    void allJsonSet(List<Object> lists);

    //有就不改 没有就新增
    @Modifying
    @Query(value = "UPDATE test_json SET info = JSON_INSERT(info, :lists) ;", nativeQuery = true)
    void allJsonInsert(List<Object> lists);

    //有就修改 没有就不改
    @Modifying
    @Query(value = "UPDATE test_json SET info = JSON_REPLACE(info, :lists) ;", nativeQuery = true)
    void allJsonReplace(List<Object> lists);

    @Modifying
    @Query(value = "UPDATE test_json SET info = JSON_REMOVE(info, :paths) ;", nativeQuery = true)
    void allJsonRemoveByPath(List<String> paths);
}
