package com.rzschool.infosys.db.vo;

import com.rzschool.infosys.db.entity.SchoolClass;
import lombok.Data;

@Data
public class SchoolClassVo extends SchoolClass {

    private String gradeName;
    private String schoolName;

}
