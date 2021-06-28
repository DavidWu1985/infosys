package com.rzschool.infosys.db.dto;

import com.rzschool.infosys.db.entity.RzUser;
import lombok.Data;

@Data
public class UserDto extends RzUser {

    private int schoolId;

    private int classId;

}
