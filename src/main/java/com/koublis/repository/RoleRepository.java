package com.koublis.repository;

import com.koublis.model.documents.ERole;
import com.koublis.model.documents.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByName(ERole name);
}
