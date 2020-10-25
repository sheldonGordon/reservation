package fr.chatelain.reservation.reservation.back.entities;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Role extends AbstractEntities {

    private static final long serialVersionUID = 1L;

    @Column
    private String libelle;

    public Role() {
        super();
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

}
