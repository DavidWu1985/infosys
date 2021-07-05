package com.rzschool.infosys.db.repository;

import com.rzschool.infosys.db.entity.ClassTeacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassTeacherRepository extends JpaRepository<ClassTeacher, Integer> {

    ClassTeacher findByUserIdAndClassId(int userId, int classId);

    List<ClassTeacher> findAllByClassId(int classId);

    void deleteAllByClassId(int classId);
}
