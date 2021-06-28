package com.rzschool.infosys.db.vo;

import com.rzschool.infosys.db.entity.RzUser;
import lombok.Data;

@Data
public class UserVo extends RzUser {

    private Integer userId;

    private Integer schoolId;

    private Integer classId;

    private String schoolName;

    private String className;


}
