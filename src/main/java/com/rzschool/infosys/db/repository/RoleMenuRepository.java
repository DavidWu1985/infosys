package com.rzschool.infosys.db.repository;

import com.rzschool.infosys.db.entity.RoleMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoleMenuRepository extends JpaRepository<RoleMenu, Integer> {


    @Query(value = "select r.role_code, m.menu_id from rz_role r, rz_role_menu m \n" +
            "where r.id = m.role_id\n" +
            "and m.menu_id in (:ids)\n" +
            "order by m.menu_id", nativeQuery = true)
    List<Object[]> getRoleMenuByMenuIds(@Param("ids") List<Integer> ids);

}
