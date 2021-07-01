package com.rzschool.infosys.db.vo;

import com.rzschool.infosys.db.entity.Lesson;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LessonVo extends Lesson {
    private String gradeName;

    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
