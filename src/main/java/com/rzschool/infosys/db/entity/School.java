package com.rzschool.infosys.db.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "rz_school")
public class School {

    @Id
    private int id;
    private String schoolName;
    private String address;
    private int masterId;

}
