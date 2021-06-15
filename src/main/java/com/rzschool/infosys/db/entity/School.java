package com.rzschool.infosys.db.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "rz_school")
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String schoolName;
    private String address;
    private int masterId;
    private Date startDate;
    private Date endDate;
    private int isDeleted;

}
