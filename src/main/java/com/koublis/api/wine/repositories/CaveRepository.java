package com.koublis.api.wine.repositories;

import com.koublis.api.wine.domain.Cave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CaveRepository extends JpaRepository<Cave, UUID> {

}