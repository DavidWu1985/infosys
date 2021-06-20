package com.rzschool.infosys.db.vo;

import com.rzschool.infosys.db.entity.Lesson;
import lombok.Data;

@Data
public class LessonVo extends Lesson {
    private String gradeName;
}
