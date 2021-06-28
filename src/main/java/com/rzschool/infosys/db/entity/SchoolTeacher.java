package com.rzschool.infosys.db.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "rz_school_teacher")
public class SchoolTeacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int userId;
    private int schoolId;

}
