package org.example.sbecommerce.repositories;

import org.example.sbecommerce.model.AppRole;
import org.example.sbecommerce.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional <Role> findByRoleName(AppRole appRole);
}
