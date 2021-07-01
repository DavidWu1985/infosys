package com.rzschool.infosys.db.entity;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "rz_student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String studentName;
    private String contact;

    @NotNull(message = "学校不能为空")
    private Integer schoolId;
    private Integer classId;

}
