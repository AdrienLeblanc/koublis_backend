package com.koublis.api.auth.repositories;

import com.koublis.api.auth.domain.ERole;
import com.koublis.api.auth.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {

    Optional<Role> findByName(ERole name);

}
