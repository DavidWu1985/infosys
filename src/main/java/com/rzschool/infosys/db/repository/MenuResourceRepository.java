package com.rzschool.infosys.db.repository;

import com.rzschool.infosys.db.entity.MenuResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MenuResourceRepository extends JpaRepository<MenuResource, Integer> {

    List<MenuResource> findAllByIsDeletedAndExcluded(int deleted, int excluded);

    @Query(value = "select m from MenuResource m where m.isDeleted = 0 and m.menuType = 1")
    List<MenuResource> findMenusLevel1ByRoleId();

    @Query(value = "select m from MenuResource m where m.isDeleted = 0 and m.menuType = 2")
    List<MenuResource> findMenusLevel2ByRoleId();

    @Query(value = "select m from MenuResource m where m.isDeleted = 0 and m.menuType = 3")
    List<MenuResource> findMenusLevel3ByRoleId();

    @Query(value = "select m from MenuResource m where m.isDeleted = 0 and m.menuType in (1, 2)")
    List<MenuResource> findAllMenusByRoleId();

}
