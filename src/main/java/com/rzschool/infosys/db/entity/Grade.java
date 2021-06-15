package com.rzschool.infosys.db.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "rz_grade")
public class Grade {
    @Id
    private int id;
    private String gradeName;

}
