package com.rzschool.infosys.db.entity;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "rz_grade")
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String gradeName;

}
