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
    /**
     * oss资源文件名
     */
    private String fileName;
    /**
     * 初始文件名
     */
    private String originalName;

    /**
     * 文件连接
     */
    private String fileLink;

}
