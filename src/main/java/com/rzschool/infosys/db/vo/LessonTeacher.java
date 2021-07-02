package com.rzschool.infosys.db.vo;

import com.rzschool.infosys.db.entity.TeacherClassLesson;
import lombok.Data;

@Data
public class LessonTeacher extends TeacherClassLesson {

    private String userName;

}
