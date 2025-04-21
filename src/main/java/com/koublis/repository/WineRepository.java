package com.koublis.repository;

import com.koublis.model.documents.Wine;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WineRepository extends MongoRepository<Wine, Long> {

    List<Wine> findAllByCaveId(Long caveId);

    Optional<Wine> findByCaveIdAndWineId(Long caveId, Long wineId);

    void deleteByCaveIdAndWineId(Long caveId, Long wineId);

}