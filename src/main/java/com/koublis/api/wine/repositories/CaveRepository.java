package com.koublis.api.wine.repositories;

import com.koublis.api.wine.domain.Cave;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaveRepository extends MongoRepository<Cave, Long> {

}