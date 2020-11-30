package com.allstate.dbservices.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.allstate.dbservices.model.Role;
import com.allstate.dbservices.model.RoleName;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(RoleName roleName);
}
