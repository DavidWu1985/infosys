package com.rzschool.infosys.db.repository;

import com.rzschool.infosys.db.entity.ClassTeacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassTeacherRepository extends JpaRepository<ClassTeacher, Integer> {

    ClassTeacher findByUserIdAndClassId(int userId, int classId);
}
