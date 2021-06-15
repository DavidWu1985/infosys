package com.rzschool.infosys.db.repository;

import com.rzschool.infosys.db.entity.SchoolClass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolClassRepository extends JpaRepository<SchoolClass, Integer> {
}
