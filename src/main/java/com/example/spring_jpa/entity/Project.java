package com.example.spring_jpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Table(name = "project")
@AllArgsConstructor
@Data
@NoArgsConstructor
@SuperBuilder
@Entity
public class Project {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "staff_project",joinColumns={@JoinColumn(name = "project_id")},inverseJoinColumns = {@JoinColumn(name = "staff_id")})
    List<Staff> staffs;


    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }


}
