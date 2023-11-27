package com.koublis.repository;

import com.koublis.model.documents.Cave;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CaveRepository extends MongoRepository<Cave, Long> {

}