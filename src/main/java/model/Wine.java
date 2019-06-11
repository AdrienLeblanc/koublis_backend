package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Wine {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String appellation;
    private String nomChateau;
    private String type;
    private long millesime;
    private long nbBouteillesAchetees;
    private long destockage;
    private long nbBouteillesStock;

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
