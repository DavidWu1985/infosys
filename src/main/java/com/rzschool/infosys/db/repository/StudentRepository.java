package com.rzschool.infosys.db.repository;

import com.rzschool.infosys.db.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {


    List<Student> findAllByClassId(int classId);
}
