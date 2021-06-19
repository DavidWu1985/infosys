package com.rzschool.infosys.db.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "rz_oss_resource")
public class OssResource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
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
