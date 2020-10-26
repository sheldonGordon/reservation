package fr.chatelain.reservation.reservation.back.entities;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Option extends AbstractEntities {

    private static final long serialVersionUID = 5335391963747990454L;

    @Column
    private String libelle;

    @Column
    private double prix;

    public Option() {
        super();
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

}
