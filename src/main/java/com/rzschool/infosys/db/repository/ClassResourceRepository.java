package com.rzschool.infosys.db.repository;

import com.rzschool.infosys.db.entity.ClassResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClassResourceRepository extends JpaRepository<ClassResource, Integer> {

    @Query(value = "select r.*, g.grade_name, l.lesson_name, o.original_name \n" +
            "from rz_class_resource r, rz_grade g, rz_lesson l, rz_oss_resource o \n" +
            "where r.grade_id = g.id and r.lesson_id = l.id and r.resource_id = o.id\n" +
            "order by r.grade_id asc, r.lesson_id ASC", nativeQuery = true)
    List<Object[]> getResourcesByGradeAndLesson();

    void deleteAllByLessonId(int id);
}
