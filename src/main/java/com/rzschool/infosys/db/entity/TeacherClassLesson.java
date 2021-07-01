package com.rzschool.infosys.db.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "rz_teacher_class_lesson")
public class TeacherClassLesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int userId;
    private int classId;
    private int lessonId;

    private LocalDateTime startTime;
    private LocalDateTime endTime;


}
