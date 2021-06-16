package com.rzschool.infosys.db.repository;


import com.rzschool.infosys.db.entity.RzRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoleRepository extends JpaRepository<RzRole, Integer> {

    @Query(value = "select r from RzRole r, UserRole u where r.id = u.roleId and u.userId = :userId")
    List<RzRole> findByUserId(@Param("userId") Integer userId);
}
