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

    private String menuType;

    private Integer parentId;

    private int isDeleted;

    private String action;

    private int excluded = 0;


}
