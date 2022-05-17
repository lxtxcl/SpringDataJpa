package com.example.spring_jpa.entity;


import com.fasterxml.jackson.databind.JsonNode;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;


import javax.persistence.*;

@TypeDefs({
        @TypeDef(name = "json", typeClass = JsonStringType.class),
        /*@TypeDef(name = "string-array", typeClass = StringArrayType.class),
        @TypeDef(name = "int-array", typeClass = IntArrayType.class),
        @TypeDef(name = "jsonb-node", typeClass = JsonNodeBinaryType.class),
        @TypeDef(name = "json-node", typeClass = JsonNodeStringType.class),*/
})
@Table(name = "test_json")
@AllArgsConstructor
@Data
@NoArgsConstructor
@SuperBuilder
@Entity
public class JsonInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @Type(type = "json")
    @Column(name = "info")
    private JsonNode info;

    @Type(type = "json")
    @Column(name = "msg")
    private JsonNode msg;
}

