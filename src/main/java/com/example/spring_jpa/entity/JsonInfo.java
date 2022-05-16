package com.example.spring_jpa.entity;


import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;


import javax.persistence.*;


@Entity
@Table(name = "test_json")
@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class JsonInfo extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /*@Type(type = "json")
    @Column(name = "info")
    private Employee info;*/

    @Type(type = "json")
    @Column(name = "info")
    private JsonNode info;

    @Type(type = "json")
    @Column(name = "msg")
    private JsonNode msg;
}

