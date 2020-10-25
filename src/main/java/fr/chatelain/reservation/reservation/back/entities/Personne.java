package fr.chatelain.reservation.reservation.back.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

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
    @Column
    private String mail;
    @Column
    private String telephone;
    @Enumerated(EnumType.STRING)
    private Civilite civilite;
    @Column
    private String adresse;
    @Column
    private LocalDate anniversaire;

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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public LocalDate getAnniversaire() {
        return anniversaire;
    }

    public void setAnniversaire(LocalDate anniversaire) {
        this.anniversaire = anniversaire;
    }

    public Civilite getCivilite() {
        return civilite;
    }

    public void setCivilite(Civilite civilite) {
        this.civilite = civilite;
    }

}
