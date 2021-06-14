package com.rzschool.infosys.db.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Data
@Entity
@Table(name = "rz_user_role")
public class UserRole {

    @Id
    private int id;
    private int userId;
    private int roleId;


}
