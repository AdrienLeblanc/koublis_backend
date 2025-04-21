package com.koublis.repositories;

import com.koublis.domain.mongo.Cave;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaveRepository extends MongoRepository<Cave, Long> {

}