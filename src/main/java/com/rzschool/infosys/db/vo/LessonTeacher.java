package com.rzschool.infosys.db.vo;

import com.rzschool.infosys.db.entity.RzUser;
import lombok.Data;

@Data
public class LessonTeacher extends RzUser {

    private int teacherLessonId;

}
