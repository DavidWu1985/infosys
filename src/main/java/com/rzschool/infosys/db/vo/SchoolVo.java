package com.rzschool.infosys.db.vo;

import com.rzschool.infosys.db.entity.School;
import lombok.Data;

@Data
public class SchoolVo extends School {

    private String masterName;

    private String statusName;

}
