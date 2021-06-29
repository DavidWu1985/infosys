package com.rzschool.infosys.db.entity;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "rz_class_teacher")
public class ClassTeacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int userId;
    private int classId;

}
