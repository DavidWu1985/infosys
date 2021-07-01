package com.rzschool.infosys.db.repository;

import com.rzschool.infosys.db.entity.SchoolClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SchoolClassRepository extends JpaRepository<SchoolClass, Integer> {

    List<SchoolClass> findBySchoolIdIn(List<Integer> collect);

    List<SchoolClass> findAllBySchoolIdOrderByIdDesc(int schoolId);

    @Query(value = "select c from SchoolClass c, ClassTeacher t where c.id = t.classId and c.schoolId = :schoolId and t.userId = :userId ")
    List<SchoolClass> getMyClassBySchoolId(@Param("schoolId") int schoolId, @Param("userId") Integer userId);
}
