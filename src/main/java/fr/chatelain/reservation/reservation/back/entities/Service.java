package fr.chatelain.reservation.reservation.back.entities;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Service extends AbstractEntities {

    private static final long serialVersionUID = 1L;

    @Column
    private String libelle;

    public Service() {
        super();
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

}
