package com.rzschool.infosys.db.repository;

import com.rzschool.infosys.db.entity.SchoolClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SchoolClassRepository extends JpaRepository<SchoolClass, Integer> {

    List<SchoolClass> findBySchoolIdIn(List<Integer> collect);

    List<SchoolClass> findAllBySchoolId(int schoolId);
}
