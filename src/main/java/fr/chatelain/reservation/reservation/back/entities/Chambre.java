package fr.chatelain.reservation.reservation.back.entities;

import java.math.BigDecimal;
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
    private Double nombrePersonne;

    @Column
    private BigDecimal prix;

    @Column
    private Double superficie;

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

    public Double getNombrePersonne() {
        return nombrePersonne;
    }

    public void setNombrePersonne(Double nombrePersonne) {
        this.nombrePersonne = nombrePersonne;
    }

    public BigDecimal getPrix() {
        return prix;
    }

    public void setPrix(BigDecimal prix) {
        this.prix = prix;
    }

    public Double getSuperficie() {
        return superficie;
    }

    public void setSuperficie(Double superficie) {
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
