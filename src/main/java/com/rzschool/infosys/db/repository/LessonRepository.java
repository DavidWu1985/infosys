package com.rzschool.infosys.db.repository;

import com.rzschool.infosys.db.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<Lesson, Integer> {
}