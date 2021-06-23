package com.rzschool.infosys.db.repository;

import com.rzschool.infosys.db.entity.MenuResource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuResourceRepository extends JpaRepository<MenuResource, Integer> {

    List<MenuResource> findAllByIsDeletedAndExcluded(int deleted, int excluded);
}
