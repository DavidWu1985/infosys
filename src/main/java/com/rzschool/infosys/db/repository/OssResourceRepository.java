package com.rzschool.infosys.db.repository;

import com.rzschool.infosys.db.entity.OssResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OssResourceRepository extends JpaRepository<OssResource, Integer> {

    @Query(value = "select r from OssResource r, ClassResource c where r.id = c.resourceId and c.lessonId = :lessonId")
    List<OssResource> getResourceByLessonId(@Param("lessonId") int lessonId);
}
