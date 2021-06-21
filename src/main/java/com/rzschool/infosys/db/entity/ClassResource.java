package com.rzschool.infosys.db.entity;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "rz_class_resource")
public class ClassResource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 资源所属等级
     */
    private Integer gradeId;

    private Integer lessonId;

    /**
     * 文件连接
     */
    private Integer resourceId;

}
