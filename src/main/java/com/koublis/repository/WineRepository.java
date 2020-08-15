package com.koublis.repository;

import com.koublis.model.documents.Wine;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WineRepository extends MongoRepository<Wine, Long> {

}