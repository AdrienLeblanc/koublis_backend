package com.koublis.api.auth.repositories;

import com.koublis.api.auth.domain.ERole;
import com.koublis.api.auth.domain.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends MongoRepository<Role, String> {

    Optional<Role> findByName(ERole name);

}
