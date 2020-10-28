package fr.chatelain.reservation.reservation.back.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Chambre extends AbstractEntities {

    private static final long serialVersionUID = 1365648515210158917L;

    @Column
    private String nom;

    @Column
    private int nombrePersonne;

    @Column
    private double prix;

    @Column
    private int superficie;

    @OneToMany
    private List<Photos> photos;

    @ManyToMany
    private List<Service> services;

    @ManyToMany
    private List<DateDebutFin> indisponibilites;

    public Chambre() {
        super();
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNombrePersonne() {
        return nombrePersonne;
    }

    public void setNombrePersonne(int nombrePersonne) {
        this.nombrePersonne = nombrePersonne;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getSuperficie() {
        return superficie;
    }

    public void setSuperficie(int superficie) {
        this.superficie = superficie;
    }

    public List<Photos> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photos> photos) {
        this.photos = photos;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public List<DateDebutFin> getIndisponibilites() {
        return indisponibilites;
    }

    public void setIndisponibilites(List<DateDebutFin> indisponibilites) {
        this.indisponibilites = indisponibilites;
    }

}
