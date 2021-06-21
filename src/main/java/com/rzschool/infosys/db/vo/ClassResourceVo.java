package com.rzschool.infosys.db.vo;

import com.rzschool.infosys.db.entity.ClassResource;
import lombok.Data;

@Data
public class ClassResourceVo extends ClassResource {

    private String lessonName;
    private String gradeName;
    private String originalName;
}
