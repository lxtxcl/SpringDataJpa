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


    //匹配所有路径是否存在
    @Query(value = "SELECT * FROM test_json t where JSON_CONTAINS_PATH(t.info,'all', :path)>0", nativeQuery = true)
    List<JsonInfo> findByAllJsonKeysExists(List<String> path);

    //匹配所有路径是否存在
    @Query(value = "SELECT * FROM test_json t where JSON_CONTAINS_PATH(t.info,'one', :path)>0", nativeQuery = true)
    List<JsonInfo> findByAnyJsonKeysExists(List<String> path);

    //判断path路径下是否匹配对应值
    @Query(value = "SELECT * FROM test_json t where JSON_CONTAINS(t.info,:value, concat('$.',:path))>0", nativeQuery = true)
    List<JsonInfo> findByJsonKeyVelueExists(String path, Object value);

    //判断数组中是否匹配对应值,或是Object中是否匹配对应键值对
    @Query(value = "SELECT * FROM test_json t where JSON_CONTAINS(t.info,:value)>0", nativeQuery = true)
    List<JsonInfo> findByJsonVelueExists(String value);


    //判断object是否存在值或键值对
    @Query(value = "SELECT * FROM test_json t where JSON_OVERLAPS(t.info,JSON_Object(:value ))>0", nativeQuery = true)
    List<JsonInfo> findByJsonObjectVelueAnyExists(List<Object> value);

    //返回所有path路径下数据不为null的数据
    @Query(value = "SELECT * FROM test_json t where NOT ISNULL(JSON_EXTRACT(t.info,:path))", nativeQuery = true)
    List<JsonInfo> findByJsonPathIndexExists(List<String> path);

    //查询存在path路径数据不为空的json
    @Query(value = "SELECT JSON_EXTRACT(t.info,:path) FROM test_json t where NOT ISNULL(JSON_EXTRACT(t.info,:path))", nativeQuery = true)
    List<String> findInfoByJsonPath(List<String> path);

    //值between
    @Query(value = "SELECT * FROM test_json t where  JSON_UNQUOTE(JSON_EXTRACT(t.info,concat('$.',:path))) BETWEEN :min AND :max", nativeQuery = true)
    List<JsonInfo> findByJsonValueBetween(String path, Object min, Object max);

    //值大于
    @Query(value = "SELECT * FROM test_json t where  JSON_UNQUOTE(JSON_EXTRACT(t.info,concat('$.',:path))) >:after", nativeQuery = true)
    List<JsonInfo> findByJsonValueAfter(String path, Object after);

    //值小于
    @Query(value = "SELECT * FROM test_json t where  JSON_UNQUOTE(JSON_EXTRACT(t.info,concat('$.',:path))) <:before", nativeQuery = true)
    List<JsonInfo> findByJsonValueBefore(String path, Object before);


    //对应path下模糊查询匹配的数据
    @Query(value = "SELECT * FROM test_json t where  JSON_UNQUOTE(JSON_EXTRACT(t.info,concat('$.',:path))) Like CONCAT(:prefix,:infill,:suffix)", nativeQuery = true)
    List<JsonInfo> findByJsonValueLike(String path, String prefix, String infill, String suffix);

    //修改或新增json
    @Modifying
    @Query(value = "UPDATE test_json SET info = JSON_SET(info, :lists) ;", nativeQuery = true)
    void allJsonSet(List<Object> lists);

    //新增json
    @Modifying
    @Query(value = "UPDATE test_json SET info = JSON_INSERT(info, :lists) ;", nativeQuery = true)
    void allJsonInsert(List<Object> lists);

    //修改json
    @Modifying
    @Query(value = "UPDATE test_json SET info = JSON_REPLACE(info, :lists) ;", nativeQuery = true)
    void allJsonReplace(List<Object> lists);

    //删除路径下的json
    @Modifying
    @Query(value = "UPDATE test_json SET info = JSON_REMOVE(info, :paths) ;", nativeQuery = true)
    void allJsonRemoveByPath(List<String> paths);

    /*------------------------------------------------------------------------------------------------------------------*/
    //判断数组是否存在值或键值对
    /*@Query(value = "SELECT * FROM test_json t where JSON_OVERLAPS(t.info,JSON_ARRAY(:value ))>0", nativeQuery = true)
    List<JsonInfo> findByJsonArrayVelueAnyExists(List<Object> value);*/


    //根据数组中对象的属性的值查询
    @Query(value = "SELECT * from test_json t where JSON_OVERLAPS(t.msg->'$[*].name',JSON_ARRAY(:value ))", nativeQuery = true)
    List<JsonInfo> findByArrayObjectValue(List<Object> value);

}
