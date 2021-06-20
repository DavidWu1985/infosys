package com.rzschool.infosys.db.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "rz_lesson")
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String lessonName;
    private Integer gradeId;

}
