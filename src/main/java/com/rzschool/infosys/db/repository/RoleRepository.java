package com.rzschool.infosys.db.repository;


import com.rzschool.infosys.db.entity.RzRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RzRole, Integer> {
}
