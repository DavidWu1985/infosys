package com.rzschool.infosys.db.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "rz_class")
public class SchoolClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String className;
    private Integer schoolId;
    private Integer gradeId;

}
