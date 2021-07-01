package com.rzschool.infosys.db.repository;


import com.rzschool.infosys.db.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SchoolRepository extends JpaRepository<School, Integer> {
    List<School> findByMasterId(Integer id);

    List<School> findAllByIdIn(List<Integer> collect);

    @Query(value = "select s from School s, SchoolTeacher t where s.id = t.schoolId and t.userId = :userId")
    List<School> findAllByUserId(@Param("userId") Integer userId);
}
