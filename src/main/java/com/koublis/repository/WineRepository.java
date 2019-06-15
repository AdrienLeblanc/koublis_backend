package com.koublis.repository;

import com.koublis.entities.Wine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WineRepository extends JpaRepository<Wine, Integer> {
    Wine findById(long id);
    List<Wine> findByAppellation(String appellation);
    List<Wine> findByNomChateau(String nomChateau);
    List<Wine> findByType(String type);
    List<Wine> findByMillesime(long millesime);
    List<Wine> findByNbBouteillesAchetees(long nbBouteillesAchetees);
    List<Wine> findByDestockage(long destockage);
    List<Wine> findByNbBouteillesStock(long nbBouteillesStock);
}