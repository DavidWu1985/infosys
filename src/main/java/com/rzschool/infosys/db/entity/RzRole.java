package com.rzschool.infosys.db.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "rz_role")
public class RzRole {
    @Id
    private int id;
    private String roleName;
    private String roleCode;
    private int sysRole;

}
