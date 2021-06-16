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
}
