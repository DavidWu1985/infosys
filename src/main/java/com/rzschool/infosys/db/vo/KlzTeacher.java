package com.rzschool.infosys.db.vo;

import com.rzschool.infosys.db.entity.ClassTeacher;
import lombok.Data;

@Data
public class KlzTeacher extends ClassTeacher {

    private String userName;
    private String account;
}
