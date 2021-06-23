package com.rzschool.infosys.db.entity;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "rz_role_menu")
public class RoleMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer roleId;

    private Integer menuId;

}
