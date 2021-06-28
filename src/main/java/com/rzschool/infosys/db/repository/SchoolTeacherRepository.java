package com.rzschool.infosys.db.repository;

import com.rzschool.infosys.db.entity.SchoolTeacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SchoolTeacherRepository extends JpaRepository<SchoolTeacher, Integer> {
    void deleteByUserIdAndSchoolId(int masterId, int schoolId);

    List<SchoolTeacher> findByUserId(Integer userId);

    List<SchoolTeacher> findBySchoolIdIn(List<Integer> collect);
}
