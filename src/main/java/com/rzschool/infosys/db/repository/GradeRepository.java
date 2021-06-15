package com.rzschool.infosys.db.repository;


import com.rzschool.infosys.db.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeRepository extends JpaRepository<Grade, Integer> {
}
