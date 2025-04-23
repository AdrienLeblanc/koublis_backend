package com.koublis.api.wine.repositories;

import com.koublis.api.wine.domain.Wine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface WineRepository extends JpaRepository<Wine, UUID> {

    @Query("SELECT w FROM Wine w WHERE w.cave.id = ?1")
    List<Wine> findAllByCaveId(UUID caveId);

    @Query("SELECT w FROM Wine w WHERE w.cave.id = ?1 AND w.id = ?2")
    Optional<Wine> findByCaveIdAndId(UUID caveId, UUID wineId);

    @Modifying
    @Query("DELETE FROM Wine w WHERE w.cave.id = ?1 AND w.id = ?2")
    void deleteByCaveIdAndId(UUID caveId, UUID wineId);

}