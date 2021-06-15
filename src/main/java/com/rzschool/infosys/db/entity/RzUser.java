package com.rzschool.infosys.db.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "rz_User")
public class RzUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String userName;
    private String account;

    @JsonIgnore
    private String userPwd;
    private Date createTime;
    private Date updateTime;
    private int isDeleted;

}
