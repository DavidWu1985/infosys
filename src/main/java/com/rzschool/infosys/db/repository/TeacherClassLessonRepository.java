package com.rzschool.infosys.db.repository;

import com.rzschool.infosys.db.entity.TeacherClassLesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TeacherClassLessonRepository extends JpaRepository<TeacherClassLesson, Integer> {

    @Query(value = "delete from TeacherClassLesson t where t.classId = :classId and t.userId = :userId")
    void deleteTeacherLessons(@Param("classId") int classId, @Param("userId") int userId);

    List<TeacherClassLesson> findAllByUserIdAndClassId(int userId, int classId);

    void deleteAllByLessonId(int id);
}
