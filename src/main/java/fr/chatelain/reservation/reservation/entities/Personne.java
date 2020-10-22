package fr.chatelain.reservation.reservation.entities;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Personne extends AbstractEntities {

    private static final long serialVersionUID = -4725443028041336733L;

    public Personne() {
        super();
    }

    @Column
    private String nom;
    @Column
    private String prenom;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

}
