package com.koublis.entities;

import javax.persistence.*;

@Entity
@Table(name = "WINE")
public class Wine {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "appellation")
    private String appellation;

    @Column(name = "nomChateau")
    private String nomChateau;

    @Column(name = "type")
    private String type;

    @Column(name = "millesime")
    private long millesime;

    @Column(name = "nbBouteillesAchetees")
    private long nbBouteillesAchetees;

    @Column(name = "destockage")
    private long destockage;

    @Column(name = "nbBouteillesStock")
    private long nbBouteillesStock;

    public Wine() {
    }

    public Wine(long id) {
        this.id = id;
    }

    public Wine(long id, String appellation, String nomChateau, String type, long millesime, long nbBouteillesAchetees, long destockage, long nbBouteillesStock) {
        this.id = id;
        this.appellation = appellation;
        this.nomChateau = nomChateau;
        this.type = type;
        this.millesime = millesime;
        this.nbBouteillesAchetees = nbBouteillesAchetees;
        this.destockage = destockage;
        this.nbBouteillesStock = nbBouteillesStock;
    }

    public Wine(long id, String appellation) {
        this.id = id;
        this.appellation = appellation;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAppellation() {
        return appellation;
    }

    public void setAppellation(String appellation) {
        this.appellation = appellation;
    }

    public String getNomChateau() {
        return nomChateau;
    }

    public void setNomChateau(String nomChateau) {
        this.nomChateau = nomChateau;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getMillesime() {
        return millesime;
    }

    public void setMillesime(long millesime) {
        this.millesime = millesime;
    }

    public long getNbBouteillesAchetees() {
        return nbBouteillesAchetees;
    }

    public void setNbBouteillesAchetees(long nbBouteillesAchetees) {
        this.nbBouteillesAchetees = nbBouteillesAchetees;
    }

    public long getDestockage() {
        return destockage;
    }

    public void setDestockage(long destockage) {
        this.destockage = destockage;
    }

    public long getNbBouteillesStock() {
        return nbBouteillesStock;
    }

    public void setNbBouteillesStock(long nbBouteillesStock) {
        this.nbBouteillesStock = nbBouteillesStock;
    }
}
