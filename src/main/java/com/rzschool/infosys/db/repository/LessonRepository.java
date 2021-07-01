package com.rzschool.infosys.db.repository;

import com.rzschool.infosys.db.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, Integer> {

    List<Lesson> findByGradeId(int gradeId);

    @Query(value = "select l from Lesson l, TeacherClassLesson tc where l.id = tc.lessonId and tc.userId = :userId and tc.classId = :classId")
    List<Lesson> findMyLessonsByClassId(@Param("userId") int userId, @Param("classId") int classId);

}
