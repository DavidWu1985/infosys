package com.rzschool.infosys.db.repository;


import com.rzschool.infosys.db.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SchoolRepository extends JpaRepository<School, Integer> {
    List<School> findByMasterId(Integer id);
}
