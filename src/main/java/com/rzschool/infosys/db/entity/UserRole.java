package com.rzschool.infosys.db.entity;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "rz_user_role")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int userId;
    private int roleId;


}
