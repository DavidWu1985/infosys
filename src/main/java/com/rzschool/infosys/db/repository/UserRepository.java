package com.rzschool.infosys.db.repository;

import com.rzschool.infosys.db.entity.RzUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<RzUser, Integer> {

    @Query(value = "select u.* from rz_user u, rz_user_role r\n" +
            "where u.id = r.user_id\n" +
            "and u.is_deleted = 0\n" +
            "and r.role_id = :roleId", nativeQuery = true)
    List<RzUser> getRzUserByRole(@Param("roleId") int roleId);

    RzUser findByAccount(String account);

    List<RzUser> findAllByIdIn(List<Integer> collect);

    @Query(value = "select u from RzUser u, ClassTeacher t where u.id = t.userId and t.classId = :classId and u.isDeleted = 0")
    List<RzUser> getClassTeachers(@Param("classId") int classId);


    @Query(value = "select u.userName, t.id from RzUser u, TeacherClassLesson t where u.id = t.userId and t.lessonId = :lessonId")
    List<Object[]> getLessonTeacherByLessonId(@Param("lessonId") int lessonId);

    @Query(value = "select u from RzUser u, SchoolTeacher s where u.id = s.userId and s.schoolId = :schoolId")
    List<RzUser> getSchoolTeacherBySchoolId(@Param("schoolId") int schoolId);
}
