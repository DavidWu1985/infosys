package com.rzschool.infosys.db.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "rz_menu_resource")
public class MenuResource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String url;

    private String menuName;

    private String method;

    private int menuType;

    private int parentId;

    private int isDeleted;

    private String action;

    private int excluded = 0;

    private String icon;

    private String path;


}
