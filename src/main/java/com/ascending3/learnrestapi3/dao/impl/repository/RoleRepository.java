package com.ascending3.learnrestapi3.dao.impl.repository;

import com.ascending3.learnrestapi3.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}
